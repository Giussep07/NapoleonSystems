package com.giussep.ricardo.napoleonsystems.dataSource.local.User;

import com.giussep.ricardo.napoleonsystems.model.UserDto;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface UserDataSource {

    Completable insertUser(UserDto userDto);

    Single<UserDto> getUserById(int userId);
}
