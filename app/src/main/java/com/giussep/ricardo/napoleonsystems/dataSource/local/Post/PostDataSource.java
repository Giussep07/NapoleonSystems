package com.giussep.ricardo.napoleonsystems.dataSource.local.Post;

import com.giussep.ricardo.napoleonsystems.model.Post;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

public interface PostDataSource {

    Completable insertPosts(List<Post> posts);

    Completable deletePost(Post post);

    Completable deleteAllPosts();

    Completable addPostToFavorite(Post post);

    Maybe<List<Post>> getPosts();

    Completable setPostLeido(Post post);

    Maybe<List<Post>> getFavoritesPosts();
}
