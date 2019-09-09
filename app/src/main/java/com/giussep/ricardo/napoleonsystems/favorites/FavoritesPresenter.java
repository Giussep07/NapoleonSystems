package com.giussep.ricardo.napoleonsystems.favorites;

import com.giussep.ricardo.napoleonsystems.model.Post;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FavoritesPresenter implements FavoritesContract.Presenter {

    private FavoritesContract.Model model;
    private FavoritesContract.View view;

    @Inject
    public FavoritesPresenter(FavoritesContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(FavoritesContract.View view) {
        this.view = view;
    }

    @Override
    public void getFavoritesPosts() {
        view.showDialogProgress();
        model.setPresenter(this);
        model.getFavoritesPosts();
    }

    @Override
    public void showFavorites(List<Post> postList) {
        if (view != null) {
            view.showFavorites(postList);
            view.hideDialogProgress();
        }
    }

    @Override
    public void showErrorLoading(String message) {
        if (view != null) {
            view.hideDialogProgress();
            view.showErrorLoading(message);
        }
    }

    @Override
    public void setPostLeido(Post post) {
        model.setPostLeido(post);
    }
}
