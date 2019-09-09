package com.giussep.ricardo.napoleonsystems.dataSource.local.User;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.giussep.ricardo.napoleonsystems.model.UserDto;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface UserDao {

    @Insert
    Completable insertUser(UserDto userDto);

    @Query("SELECT * FROM User WHERE id = :userId;")
    Single<UserDto> getUserById(int userId);
}
