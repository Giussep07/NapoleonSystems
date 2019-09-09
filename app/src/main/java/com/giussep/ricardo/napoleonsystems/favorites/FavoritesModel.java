package com.giussep.ricardo.napoleonsystems.favorites;

import com.giussep.ricardo.napoleonsystems.model.Post;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FavoritesModel implements FavoritesContract.Model {

    private FavoritesContract.Repository repository;
    private FavoritesContract.Presenter presenter;

    private Disposable disposable;

    @Inject
    public FavoritesModel(FavoritesContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void setPresenter(FavoritesContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getFavoritesPosts() {
        disposable = repository.getPostsFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        posts -> presenter.showFavorites(posts),
                        throwable -> {
                            presenter.showErrorLoading(throwable.getLocalizedMessage());
                            throwable.printStackTrace();
                        }
                );
    }

    @Override
    public void setPostLeido(Post post) {
        disposable = repository.updatePost(post)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> System.out.println("Post leido"),
                        Throwable::printStackTrace
                );
    }
}
