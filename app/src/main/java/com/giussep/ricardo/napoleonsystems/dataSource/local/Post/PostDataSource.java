package com.giussep.ricardo.napoleonsystems.dataSource.local.Post;

import com.giussep.ricardo.napoleonsystems.model.Post;

import java.util.List;

import io.reactivex.Completable;

public interface PostDataSource {

    Completable insertPosts(List<Post> posts);

    Completable deletePost(Post post);

    Completable deleteAllPosts();

    Completable addPostToFavorite(Post post);
}
