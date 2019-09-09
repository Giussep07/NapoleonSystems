package com.giussep.ricardo.napoleonsystems.dataSource.local.User;

import com.giussep.ricardo.napoleonsystems.model.UserDto;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class UserLocalDataSource implements UserDataSource {

    private UserDao dao;

    @Inject
    public UserLocalDataSource(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public Single<UserDto> getUserById(int userId) {
        return dao.getUserById(userId);
    }

    @Override
    public Completable insertUser(UserDto userDto) {
        return dao.insertUser(userDto);
    }


}
