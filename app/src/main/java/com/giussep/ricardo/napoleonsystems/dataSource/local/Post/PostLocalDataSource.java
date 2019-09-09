package com.giussep.ricardo.napoleonsystems.dataSource.local.Post;

import com.giussep.ricardo.napoleonsystems.model.Post;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;

public class PostLocalDataSource implements PostDataSource {

    private PostDao postDao;

    @Inject
    public PostLocalDataSource(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public Completable insertPosts(List<Post> posts) {
        return postDao.insertPosts(posts);
    }

    @Override
    public Completable deletePost(Post post) {
        return postDao.deletePost(post);
    }

    @Override
    public Completable deleteAllPosts() {
        return postDao.deleteAllPosts();
    }

    @Override
    public Completable addPostToFavorite(Post post) {
        return postDao.addPostToFavorite(post);
    }
}
