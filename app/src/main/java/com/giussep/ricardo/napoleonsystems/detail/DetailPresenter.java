package com.giussep.ricardo.napoleonsystems.detail;

import com.giussep.ricardo.napoleonsystems.model.UserApi;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.Model model;
    private DetailContract.View view;

    private Disposable disposable;

    @Inject
    public DetailPresenter(DetailContract.Model model) {
        this.model = model;
    }

    @Override
    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void setView(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getUserInfo(int userId) {
        model.setPresenter(this);
        view.showDialogProgress();
        model.getUserInfo(userId);
    }

    @Override
    public void showUserInfo(UserApi userApi) {
        if (view != null) {
            view.showUserInfo(userApi);
            view.hideDialogProgress();
        }
    }

    @Override
    public void showErrorLoadingUserInfo(String message) {
        if (view != null) {
            view.hideDialogProgress();
            view.showErrorLoadingUserInfo(message);
        }
    }
}
