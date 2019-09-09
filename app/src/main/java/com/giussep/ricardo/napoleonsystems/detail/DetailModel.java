package com.giussep.ricardo.napoleonsystems.detail;

import androidx.room.EmptyResultSetException;

import com.giussep.ricardo.napoleonsystems.model.Address;
import com.giussep.ricardo.napoleonsystems.model.Company;
import com.giussep.ricardo.napoleonsystems.model.Geo;
import com.giussep.ricardo.napoleonsystems.model.UserApi;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailModel implements DetailContract.Model {

    private DetailContract.Repository repository;
    private DetailContract.Presenter presenter;

    private Disposable disposable;

    @Inject
    public DetailModel(DetailContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getUserInfo(int userId) {

        disposable = repository.existUserById(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userDto -> {
                            UserApi userApi = new UserApi(userDto);

                            presenter.showUserInfo(userApi);
                        },
                        throwable -> {
                            if (throwable instanceof EmptyResultSetException) {

                                disposable = repository.getUserInfo(userId)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(
                                                user -> disposable = repository.insertUser(user)
                                                        .subscribeOn(Schedulers.io())
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        .subscribe(
                                                                () -> presenter.showUserInfo(user),
                                                                throwable1 -> {
                                                                    presenter.showErrorLoadingUserInfo(throwable1.getLocalizedMessage());
                                                                    throwable1.printStackTrace();
                                                                }
                                                        ),
                                                throwable1 -> {
                                                    presenter.showErrorLoadingUserInfo(throwable1.getLocalizedMessage());
                                                    throwable1.printStackTrace();
                                                }
                                        );
                            } else {

                                presenter.showErrorLoadingUserInfo(throwable.getLocalizedMessage());
                                throwable.printStackTrace();
                            }
                        }
                );
    }
}
