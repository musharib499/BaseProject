/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.innobles.smartplaykids.ui.base;

/**
 * Created by janisharali on 27/01/17.
 */

import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import com.innobles.smartplaykids.application.SmartApplication;
import com.innobles.smartplaykids.network.model.Login;
import com.innobles.smartplaykids.utils.ComplexPreferences;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface MvpView {

    void onShowLoading(String message);

    void onHideLoading();

    void openActivityOnTokenExpire();

    void onError(@StringRes int resId);

    void onError(String message);
    void onErrorThrowable(Throwable e);

    void onErrorFullScreen(String message);
    void onClickAction();
    void setTitleMessage(String titile);

    void onBottomNavigationShow();
    void onBottomNavigationHide();

    FrameLayout onMainFrameLayout();
    Button onButtonRetry();
    void onShowMessage(String message);

    void onShowMessage(@StringRes int resId);
    boolean onNetworkConnected();
    void onHideKeyboard();

    void onLoadedSuccess(Object o);
    void onLoadedFailure(Throwable error);
    void onShowProgressBar();
    void onNextActivity(int activity,Object o);
    void onNextActivity(int activity);
    void onBackArrowShow();
    TabLayout onTabLayout();
    SmartApplication getSmartApplication();
    ComplexPreferences getComplexPreferences();

}
