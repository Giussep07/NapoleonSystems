package com.giussep.ricardo.napoleonsystems.home;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    private PostAdapter postAdapter;

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

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
        if (item.getItemId() == R.id.action_delete_all) {
            postAdapter.deleteAllItems();
        }

        return super.onOptionsItemSelected(item);
    }

    //region Implementation HomeContract.View
    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showData(List<Post> posts) {

        //Obtenemos los 20 primeros posts
        for (int i = 0; i < 20; i++) {
            Post post = posts.get(i);

            post.setLeido(false);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        postAdapter = new PostAdapter(posts, this);

        recyclerView.setAdapter(postAdapter);

        SimpleItemTouchHelperCallback simpleItemTouchHelperCallback = new SimpleItemTouchHelperCallback(getContext(), 0, ItemTouchHelper.START, postAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchHelperCallback);

        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showAllPostsDeleted() {
        Toast.makeText(getContext(), "Todos los posts han sido eliminados", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postAddedToFavorite() {
        Toast.makeText(getContext(), "Post agregado como favorito", Toast.LENGTH_SHORT).show();
    }

    //endregion

    //region Implementation PostAdapter.OnPostAdapter

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