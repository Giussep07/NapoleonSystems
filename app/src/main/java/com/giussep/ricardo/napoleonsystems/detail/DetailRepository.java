package com.giussep.ricardo.napoleonsystems.detail;

import com.giussep.ricardo.napoleonsystems.dataSource.local.User.UserDataSource;
import com.giussep.ricardo.napoleonsystems.model.UserApi;
import com.giussep.ricardo.napoleonsystems.model.UserDto;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Retrofit;

public class DetailRepository implements DetailContract.Repository {

    private UserDataSource userDataSource;
    private Retrofit retrofit;

    @Inject
    public DetailRepository(UserDataSource userDataSource, Retrofit retrofit) {
        this.userDataSource = userDataSource;
        this.retrofit = retrofit;
    }

    @Override
    public Completable insertUser(UserApi user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setStreet(user.getAddress().getStreet());
        userDto.setSuite(user.getAddress().getSuite());
        userDto.setCity(user.getAddress().getCity());
        userDto.setZipcode(user.getAddress().getZipcode());
        userDto.setLat(user.getAddress().getGeo().getLat());
        userDto.setLng(user.getAddress().getGeo().getLng());
        userDto.setPhone(user.getPhone());
        userDto.setWebsite(user.getWebsite());
        userDto.setCompanyName(user.getCompany().getName());
        userDto.setCatchPhrase(user.getCompany().getCatchPhrase());
        userDto.setBs(user.getCompany().getBs());

        return userDataSource.insertUser(userDto);
    }

    @Override
    public Single<UserDto> existUserById(int userId) {
        return userDataSource.getUserById(userId);
    }

    @Override
    public Observable<UserApi> getUserInfo(int userId) {
        com.giussep.ricardo.napoleonsystems.dataSource.remote.UserApi userApi = retrofit.create(com.giussep.ricardo.napoleonsystems.dataSource.remote.UserApi.class);
        return userApi.getUserById(String.valueOf(userId));
    }
}
