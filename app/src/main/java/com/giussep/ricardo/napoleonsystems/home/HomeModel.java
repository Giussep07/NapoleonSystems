package com.giussep.ricardo.napoleonsystems.home;

import com.giussep.ricardo.napoleonsystems.dataSource.remote.PostApi;
import com.giussep.ricardo.napoleonsystems.model.Post;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class HomeModel implements HomeContract.Model {

    private HomeContract.Repository repository;

    @Inject
    public HomeModel(HomeContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<Post>> getPosts() {
        return repository.getPosts();
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
}
