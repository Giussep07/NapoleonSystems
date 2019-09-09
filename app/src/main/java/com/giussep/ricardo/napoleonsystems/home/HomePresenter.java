package com.giussep.ricardo.napoleonsystems.home;

import com.giussep.ricardo.napoleonsystems.model.Post;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private HomeContract.Model model;

    private Disposable disposable;

    @Inject
    public HomePresenter(HomeContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void getPosts() {
        if (view != null) {

            view.showProgress();

            disposable = new CompositeDisposable();

            disposable = model.getPosts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(posts -> {
                        view.showData(posts);
                        view.hideProgress();

                        disposable = model.insertPosts(posts)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(() -> System.out.println("Guardo registros"),
                                        Throwable::printStackTrace);
                    }, throwable -> {
                        view.hideProgress();
                        view.showError(throwable.getLocalizedMessage());
                    });
        }
    }

    @Override
    public void deletePost(Post post) {
        disposable = model.deletePost(post)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> System.out.println("Post eliminado"),
                        Throwable::printStackTrace
                );
    }

    @Override
    public void deleteAllPosts() {
        disposable = model.deleteAllPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.showAllPostsDeleted(),
                        Throwable::printStackTrace
                );
    }

    @Override
    public void addPostToFavorite(Post post) {
        disposable = model.addPostToFavorite(post).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.postAddedToFavorite(),
                        Throwable::printStackTrace
                );
    }
}
