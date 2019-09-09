package com.giussep.ricardo.napoleonsystems.home;

import com.giussep.ricardo.napoleonsystems.dataSource.remote.PostApi;
import com.giussep.ricardo.napoleonsystems.model.Post;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements HomeContract.Model {

    private HomeContract.Repository repository;
    private HomeContract.Presenter presenter;

    private Disposable disposable;

    @Inject
    public HomeModel(HomeContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getPosts() {
        disposable = repository.getLocalPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        posts -> {
                            if (posts.isEmpty()) {
                                disposable = repository.getPosts()
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(
                                                posts1 -> {

                                                    for (int i = 0; i < 20; i++) {
                                                        Post post = posts1.get(i);
                                                        post.setLeido(0);
                                                    }

                                                    disposable = repository.insertPosts(posts1)
                                                            .subscribeOn(Schedulers.io())
                                                            .observeOn(AndroidSchedulers.mainThread())
                                                            .subscribe(
                                                                    () -> presenter.showData(posts1),
                                                                    throwable -> {
                                                                        presenter.showError(throwable.getLocalizedMessage());
                                                                        throwable.printStackTrace();
                                                                    }
                                                            );
                                                },
                                                throwable -> {
                                                    presenter.showError(throwable.getLocalizedMessage());
                                                    throwable.printStackTrace();
                                                }
                                        );
                            } else {
                                presenter.showData(posts);
                            }
                        },
                        throwable -> {
                            presenter.showError(throwable.getLocalizedMessage());
                            throwable.printStackTrace();
                        }
                );
    }

    @Override
    public Completable insertPosts(List<Post> posts) {
        return repository.insertPosts(posts);
    }

    @Override
    public Completable deletePost(Post post) {
        return repository.deletePost(post);
    }

    @Override
    public Completable deleteAllPosts() {
        return repository.deleteAllPosts();
    }

    @Override
    public Completable addPostToFavorite(Post post) {
        return repository.addPostToFavorite(post);
    }

    @Override
    public Completable setPostLeido(Post post) {
        return repository.setPostLeido(post);
    }
}
