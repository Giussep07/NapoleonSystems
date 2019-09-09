package com.giussep.ricardo.napoleonsystems.detail;

import com.giussep.ricardo.napoleonsystems.model.UserApi;
import com.giussep.ricardo.napoleonsystems.model.UserDto;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public interface DetailContract {

    interface Model {

        void setPresenter(DetailContract.Presenter presenter);

        void getUserInfo(int userId);
    }

    interface View {
        void showDialogProgress();

        void hideDialogProgress();

        void showUserInfo(UserApi userApi);

        void showErrorLoadingUserInfo(String message);
    }

    interface Presenter {

        void setView(DetailContract.View view);

        void getUserInfo(int userId);

        void showUserInfo(UserApi userApi);

        void showErrorLoadingUserInfo(String message);

        void dispose();
    }

    interface Repository {

        Single<UserDto> existUserById(int userId);

        Observable<UserApi> getUserInfo(int userId);

        Completable insertUser(UserApi user);
    }

}
