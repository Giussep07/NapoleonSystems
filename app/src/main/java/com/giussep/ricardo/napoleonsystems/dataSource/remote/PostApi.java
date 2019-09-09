package com.giussep.ricardo.napoleonsystems.dataSource.remote;

import com.giussep.ricardo.napoleonsystems.model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PostApi {

    @GET("posts")
    Observable<List<Post>> getPosts();
}
