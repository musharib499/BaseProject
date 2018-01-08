package com.innobles.smartplaykids.ui.fragment.login;

import com.innobles.smartplaykids.network.model.Login;
import com.innobles.smartplaykids.network.retrofit.RestApiCalls;
import com.innobles.smartplaykids.ui.base.MvpPresenter;
import com.innobles.smartplaykids.ui.base.MvpView;
import com.innobles.smartplaykids.utils.AppLogger;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Mushareb Ali on 31,December,2017.
 * ali.musharib1@gmail.com
 */

public class LoginPresenter implements MvpPresenter {
    private static final String TAG = "LoginPresenter";
    MvpView mvpView;
    Disposable disposable;

    public LoginPresenter(MvpView view) {
        this.mvpView = view;
    }

    @Override
    public void onHashMapParam(int command ,HashMap<String, String> param) {
        RestApiCalls.getLogin(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Login>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        mvpView.onShowProgressBar();
                        AppLogger.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Login value) {
                        mvpView.onLoadedSuccess(value);
                        mvpView.onHideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpView.onHideLoading();
                        mvpView.onLoadedFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        mvpView.onHideLoading();
                        disposable.isDisposed();
                    }
                });
    }
}
