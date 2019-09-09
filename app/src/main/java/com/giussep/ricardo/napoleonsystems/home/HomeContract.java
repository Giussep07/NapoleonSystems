package com.giussep.ricardo.napoleonsystems.home;

import com.giussep.ricardo.napoleonsystems.model.Post;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface HomeContract {

    interface Model {
        Observable<List<Post>> getPosts();

        Completable insertPosts(List<Post> posts);

        Completable deletePost(Post post);

        Completable deleteAllPosts();

        Completable addPostToFavorite(Post post);
    }

    interface View {
        void showProgress();

        void hideProgress();

        void showData(List<Post> posts);

        void showError(String error);

        void showAllPostsDeleted();

        void postAddedToFavorite();
    }

    interface Presenter {
        void setView(HomeContract.View view);

        void getPosts();

        void deletePost(Post post);

        void dispose();

        void deleteAllPosts();

        void addPostToFavorite(Post post);
    }

    interface Repository {
        Observable<List<Post>> getPosts();

        Completable insertPosts(List<Post> posts);

        Completable deletePost(Post post);

        Completable deleteAllPosts();

        Completable addPostToFavorite(Post post);
    }
}
