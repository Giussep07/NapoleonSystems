package com.giussep.ricardo.napoleonsystems.favorites;

import com.giussep.ricardo.napoleonsystems.dataSource.local.Post.PostDataSource;
import com.giussep.ricardo.napoleonsystems.model.Post;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

public class FavoritesRepository implements FavoritesContract.Repository {

    private PostDataSource dataSource;

    @Inject
    public FavoritesRepository(PostDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Maybe<List<Post>> getPostsFavorites() {
        return dataSource.getFavoritesPosts();
    }

    @Override
    public Completable updatePost(Post post) {
        return dataSource.setPostLeido(post);
    }
}
