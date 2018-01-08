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

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.innobles.smartplaykids.R;
import com.innobles.smartplaykids.application.SmartApplication;
import com.innobles.smartplaykids.utils.CommonUtils;
import com.innobles.smartplaykids.utils.ComplexPreferences;
import com.innobles.smartplaykids.utils.NetworkUtils;
import com.innobles.smartplaykids.utils.Utils;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import butterknife.Unbinder;
import okhttp3.ResponseBody;


/**
 * Created by janisharali on 27/01/17.
 */

public abstract class BaseActivity extends AppCompatActivity
        implements MvpView, BaseFragment.Callback,View.OnClickListener {

    private ProgressDialog mProgressDialog;
    private Unbinder mUnBinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* setUnBinder(ButterKnife.bind(this));*/

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onShowLoading(String message) {
        onHideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this,message);
    }


    @Override
    public void onHideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }


    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }


    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void onShowMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onShowMessage(@StringRes int resId) {
        onShowMessage(getString(resId));
    }

    @Override
    public boolean onNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }


    public void onHideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void openActivityOnTokenExpire() {
        //startActivity(LoginFragment.getStartIntent(this));
        finish();
    }

    @Override
    public void onLoadedFailure(Throwable error) {
        onErrorThrowable(error);
    }

    @Override
    public void onNextActivity(int activity,Object o) {

    }

    @Override
    public void onNextActivity(int activity) {

    }

    @Override
    public void onBackArrowShow() {

    }

    @Override
    public ComplexPreferences getComplexPreferences() {
        return getSmartApplication().getComplexPreference();
    }

    @Override
    public SmartApplication getSmartApplication() {
        if (null != getApplication() && getApplication() instanceof SmartApplication)
            return (SmartApplication) getApplication();
        else
            return null;
    }

    @Override
    public void onErrorThrowable(Throwable e) {
         onHideLoading();
        if (e instanceof HttpException) {
            ResponseBody responseBody = ((HttpException)e).response().errorBody();
            onError(Utils.getErrorMessage(responseBody));
        } else if (e instanceof SocketTimeoutException) {
            onError("Please try again Time out....");
        } else if (e instanceof IOException) {
            onError(getResources().getString(R.string.connection_error));
        } else {
            onError(e.getMessage());
        }
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }

        super.onDestroy();
    }

    protected abstract void setUp();

    @Override
    public void onBackPressed() {


        android.app.FragmentManager fragmentManager = getFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        if (count > 0 /*&& !(getFragment() instanceof MyReqCreateNewFragment)*/) {
            getFragmentManager().popBackStack();

        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
               onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    /*
* Method is used to replace fragment
* */
    public void replaceFragment(Fragment fragment, Bundle bundle, boolean addToBackStack) {
        if (bundle != null) fragment.setArguments(bundle);
        String tag = fragment.getClass().getSimpleName();
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fbaseLayoutMain, fragment, tag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragment.setRetainInstance(true);
        if (addToBackStack)
            ft.addToBackStack(tag);
        try {
            ft.commit();
        } catch (Exception ex) {
            // C24.log(getApplicationContext(), LifeCycleMethods.CATCH, "replaceFragment" + ex.getMessage(), getClass().getName(), C24.Level.WARNING);
            ex.printStackTrace();
            ft.addToBackStack(null);
        }
    }
}
