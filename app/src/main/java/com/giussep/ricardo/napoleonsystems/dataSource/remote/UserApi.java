package com.giussep.ricardo.napoleonsystems.dataSource.remote;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {

    @GET("users/{userId}")
    Observable<com.giussep.ricardo.napoleonsystems.model.UserApi> getUserById(@Path("userId") String userId);
}
