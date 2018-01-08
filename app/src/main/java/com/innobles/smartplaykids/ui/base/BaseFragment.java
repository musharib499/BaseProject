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

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import com.innobles.smartplaykids.application.SmartApplication;
import com.innobles.smartplaykids.utils.CommonUtils;
import com.innobles.smartplaykids.utils.ComplexPreferences;

import butterknife.Unbinder;

/**
 * Created by janisharali on 27/01/17.
 */

public abstract class BaseFragment extends Fragment implements MvpView, View.OnClickListener {

    private MainBaseActivity mActivity;
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainBaseActivity) {
            MainBaseActivity activity = (MainBaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onShowLoading(String message) {
        onHideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(getActivity(), message);
    }

    @Override
    public void onHideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(String message) {
        if (mActivity != null) {
            mActivity.onError(message);
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.onError(resId);
        }
    }

    @Override
    public void onShowMessage(String message) {
        if (mActivity != null) {
            mActivity.onShowMessage(message);
        }
    }

    @Override
    public TabLayout onTabLayout() {
        if (mActivity != null) {
            return mActivity.onTabLayout();
        } else {
            return null;
        }
    }

    @Override
    public void onShowMessage(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.onShowMessage(resId);
        }
    }

    @Override
    public SmartApplication getSmartApplication() {
        if (mActivity != null) {
            return mActivity.getSmartApplication();
        } else {
            return null;
        }
    }

    @Override
    public ComplexPreferences getComplexPreferences() {
        if (mActivity != null) {
            return mActivity.getComplexPreferences();
        } else {
            return null;
        }
    }

    @Override
    public boolean onNetworkConnected() {
        if (mActivity != null) {
            return mActivity.onNetworkConnected();
        }
        return false;
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onHideKeyboard() {
        if (mActivity != null) {
            mActivity.onHideKeyboard();
        }
    }

    @Override
    public void onNextActivity(int activity) {
        if (mActivity != null)
            mActivity.onNextActivity(activity);
    }

    @Override
    public void onNextActivity(int activity, Object o) {
        if (mActivity != null)
            mActivity.onNextActivity(activity, o);
    }


    @Override
    public void onErrorThrowable(Throwable e) {
        if (mActivity != null)
            mActivity.onErrorThrowable(e);
    }

    @Override
    public void onErrorFullScreen(String message) {
        if (mActivity != null)
            mActivity.onErrorFullScreen(message);
    }

    @Override
    public void onClickAction() {
        if (mActivity != null)
            mActivity.onClickAction();
    }

    @Override
    public void setTitleMessage(String titile) {
        if (mActivity != null)
            mActivity.setTitleMessage(titile);
    }

    @Override
    public void onBottomNavigationShow() {
        if (mActivity != null)
            mActivity.onBottomNavigationShow();
    }

    @Override
    public void onBottomNavigationHide() {
        if (mActivity != null)
            mActivity.onBottomNavigationShow();
    }

    @Override
    public FrameLayout onMainFrameLayout() {
        if (mActivity != null)
            mActivity.onMainFrameLayout();
        return null;
    }

    @Override
    public Button onButtonRetry() {
        if (mActivity != null) {
            return mActivity.onButtonRetry();
        } else {
            return null;
        }
    }

    @Override
    public void onLoadedSuccess(Object o) {
        if (mActivity != null)
            mActivity.onLoadedSuccess(o);
    }

    @Override
    public void onLoadedFailure(Throwable error) {
        if (mActivity != null)
            mActivity.onLoadedFailure(error);
    }

    @Override
    public void onShowProgressBar() {
        if (mActivity != null)
            mActivity.onShowProgressBar();
    }

    @Override
    public void onBackArrowShow() {
        if (mActivity != null)
            mActivity.onBackArrowShow();
    }

    @Override
    public void openActivityOnTokenExpire() {
        if (mActivity != null) {
            mActivity.openActivityOnTokenExpire();
        }


    }


    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    protected abstract void setUp(View view);

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
