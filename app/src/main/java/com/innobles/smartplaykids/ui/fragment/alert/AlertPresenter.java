package com.innobles.smartplaykids.ui.fragment.alert;

import android.util.Log;

import com.innobles.smartplaykids.network.model.StudentInfo;
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
 * Created by Mushareb Ali on 07,January,2018.
 * ali.musharib1@gmail.com
 */

public class AlertPresenter implements MvpPresenter {
    private static final String TAG = "LoginPresenter";
    MvpView mvpView;
    Disposable disposable;

    public AlertPresenter(MvpView view) {
        this.mvpView = view;
    }

    @Override
    public void onHashMapParam(int command,HashMap<String, String> param) {

        RestApiCalls.getStudentInfo(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StudentInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        mvpView.onShowProgressBar();
                        AppLogger.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(StudentInfo value) {
                        Log.d(TAG,value.toString());
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
