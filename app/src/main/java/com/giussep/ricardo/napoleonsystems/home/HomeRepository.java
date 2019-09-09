package com.giussep.ricardo.napoleonsystems.home;

import com.giussep.ricardo.napoleonsystems.dataSource.local.Post.PostLocalDataSource;
import com.giussep.ricardo.napoleonsystems.dataSource.remote.PostApi;
import com.giussep.ricardo.napoleonsystems.model.Post;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import retrofit2.Retrofit;

public class HomeRepository implements HomeContract.Repository {

    private PostLocalDataSource postLocalDataSource;
    private Retrofit retrofit;

    @Inject
    public HomeRepository(PostLocalDataSource postLocalDataSource, Retrofit retrofit) {
        this.postLocalDataSource = postLocalDataSource;
        this.retrofit = retrofit;
    }

    @Override
    public Maybe<List<Post>> getLocalPosts() {
        return postLocalDataSource.getPosts();
    }

    @Override
    public Observable<List<Post>> getPosts() {
        PostApi postApi = retrofit.create(PostApi.class);
        return postApi.getPosts();
    }

    @Override
    public Completable insertPosts(List<Post> posts) {
        return postLocalDataSource.insertPosts(posts);
    }

    @Override
    public Completable deletePost(Post post) {
        return postLocalDataSource.deletePost(post);
    }

    @Override
    public Completable deleteAllPosts() {
        return postLocalDataSource.deleteAllPosts();
    }

    @Override
    public Completable addPostToFavorite(Post post) {
        return postLocalDataSource.addPostToFavorite(post);
    }

    @Override
    public Completable setPostLeido(Post post) {
        return postLocalDataSource.setPostLeido(post);
    }
}
