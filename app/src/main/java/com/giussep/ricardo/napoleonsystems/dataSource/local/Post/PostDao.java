package com.giussep.ricardo.napoleonsystems.dataSource.local.Post;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.giussep.ricardo.napoleonsystems.model.Post;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPosts(List<Post> posts);

    @Delete
    Completable deletePost(Post post);

    @Query("DELETE FROM Post")
    Completable deleteAllPosts();

    @Update
    Completable addPostToFavorite(Post post);
}
