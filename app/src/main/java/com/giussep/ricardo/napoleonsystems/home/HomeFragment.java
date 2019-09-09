package com.giussep.ricardo.napoleonsystems.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.giussep.ricardo.napoleonsystems.R;
import com.giussep.ricardo.napoleonsystems.adapters.PostAdapter;
import com.giussep.ricardo.napoleonsystems.model.Post;
import com.giussep.ricardo.napoleonsystems.root.ApplicationClass;
import com.giussep.ricardo.napoleonsystems.utils.SimpleItemTouchHelperCallback;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeContract.View, PostAdapter.OnPostAdapter {

    @Inject
    HomeContract.Presenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private PostAdapter postAdapter;
    private View view;

    @Override
    public void onAttach(Context context) {
        ((ApplicationClass) context.getApplicationContext()).getComponent().inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        swipeRefresh.setOnRefreshListener(() -> presenter.getPosts());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getPosts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_delete_all:
                postAdapter.deleteAllItems();
                break;
            case R.id.action_all_favorites:
                Navigation.findNavController(view).navigate(HomeFragmentDirections.homeFragmentToFavoritesFragment());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //region Implementation HomeContract.View
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(List<Post> posts) {
        swipeRefresh.setRefreshing(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        postAdapter = new PostAdapter(posts, this);

        recyclerView.setAdapter(postAdapter);

        postAdapter.notifyDataSetChanged();

        SimpleItemTouchHelperCallback simpleItemTouchHelperCallback = new SimpleItemTouchHelperCallback(getContext(), 0, ItemTouchHelper.START, postAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchHelperCallback);

        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showAllPostsDeleted() {
        Toast.makeText(getContext(), "Todos los posts han sido eliminados", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postAddedToFavorite(Post post) {
        Toast.makeText(getContext(), String.format("Post %s como favorito", post.getFavorite() == 1 ? "agregado" : "eliminado"), Toast.LENGTH_SHORT).show();
    }

    //endregion

    //region Implementation PostAdapter.OnPostAdapter

    @Override
    public void onPostClicked(Post post) {

        if (post.getLeido() == 0) {
            post.setLeido(1);
            presenter.setPostLeido(post);
        }

        HomeFragmentDirections.ActionHomeFragmentToDetailFragment2 action = HomeFragmentDirections.actionHomeFragmentToDetailFragment2();

        action.setUserId(post.getUserId());

        Navigation.findNavController(view).navigate(action);

    }

    @Override
    public void onPostDelete(Post post) {
        presenter.deletePost(post);
    }

    @Override
    public void onAllDeleteComplete() {
        presenter.deleteAllPosts();
    }

    @Override
    public void onPostFavorite(Post post) {
        presenter.addPostToFavorite(post);
        postAdapter.notifyDataSetChanged();
    }

    //endregion
}
