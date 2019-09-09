package com.giussep.ricardo.napoleonsystems.favorites;


import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.giussep.ricardo.napoleonsystems.R;
import com.giussep.ricardo.napoleonsystems.adapters.PostAdapter;
import com.giussep.ricardo.napoleonsystems.model.Post;
import com.giussep.ricardo.napoleonsystems.root.ApplicationClass;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment implements FavoritesContract.View, PostAdapter.OnPostAdapter {

    @Inject
    FavoritesContract.Presenter presenter;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private View view;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        ((ApplicationClass) context.getApplicationContext()).getComponent().inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favorites, container, false);

        ButterKnife.bind(this, view);

        if (getActivity() != null) {

            if (getActivity().getActionBar() != null) {

                ActionBar actionBar = getActivity().getActionBar();
                actionBar.setTitle("Favoritos");
            }
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getFavoritesPosts();
    }

    //region Implementation FavoritesContract.View

    @Override
    public void showDialogProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideDialogProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFavorites(List<Post> postList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        PostAdapter postAdapter = new PostAdapter(postList, this);

        recyclerView.setAdapter(postAdapter);
    }

    @Override
    public void showErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    //endregion

    //region PostAdapter.OnPostAdapter
    @Override
    public void onPostClicked(Post post) {
        if (post.getLeido() == 0) {
            post.setLeido(1);
            presenter.setPostLeido(post);
        }

        FavoritesFragmentDirections.FavoritesFragmentToDetailFragment action = FavoritesFragmentDirections.favoritesFragmentToDetailFragment();

        action.setUserId(post.getUserId());

        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onPostDelete(Post post) {

    }

    @Override
    public void onAllDeleteComplete() {

    }

    @Override
    public void onPostFavorite(Post post) {

    }
    //endregion

}
