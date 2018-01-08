package com.innobles.smartplaykids.application;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.innobles.smartplaykids.R;
import com.innobles.smartplaykids.fcm.FCMRegister;
import com.innobles.smartplaykids.network.model.Login;
import com.innobles.smartplaykids.utils.AppLogger;
import com.innobles.smartplaykids.utils.ComplexPreferences;
import com.innobles.smartplaykids.utils.Constant;


/**
 * Created by mushareb on 28/12/2017.
 */

public class SmartApplication extends MultiDexApplication {
    private ComplexPreferences complexPreferences = null;
    private Login login;
    private FCMRegister mFcmRegister;
    /*private IGCMToken igcmToken;*/


    @Override
    public void onCreate() {
        super.onCreate();
        setUpFCM();
        AppLogger.init();

        complexPreferences = ComplexPreferences.getComplexPreferences(getApplicationContext(), getString(R.string.app_name));


    }
    public Login getLoginResponse() {
        if (login == null ) {
            login = complexPreferences.getObject(Constant.LOGIN_KEY, Login.class);
        }
        return login!=null ?login :null ;
    }


    /**
     * @param mLoginResponse StateResponse
     */
    public void setLoginResponse(Login mLoginResponse) {
        if (mLoginResponse != null) {
            complexPreferences.putObject(Constant.LOGIN_KEY, mLoginResponse);
            complexPreferences.commit();
        }
        this.login = mLoginResponse;
    }
    public ComplexPreferences getComplexPreference() {
        if (complexPreferences != null) {
            return complexPreferences;
        }
        return null;
    }
    public ApplicationInfo getApplicationMetaData()
            throws PackageManager.NameNotFoundException {
        return getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
    }
    public void setUpFCM()
    {
        FirebaseApp.initializeApp(this.getApplicationContext());
       /* if(BuildConfig.VERSION_NAME.contains("pro")) {
            FirebaseMessaging.getInstance().subscribeToTopic(Constant.FCM_SUBSCRIPTION_LIVE);
        }else {
            FirebaseMessaging.getInstance().subscribeToTopic(Constant.FCM_SUBSCRIPTION_DEV);
        }*/

    }
    public void initializeFcmToken() {
//        String baseUrl = IWebServicesModel.getBaseUrl();
//        if (!TextUtils.isEmpty(baseUrl)) {
        mFcmRegister = new FCMRegister(this) {
            @Override
            protected void sendRegistrationIdToBackend(String regId) {
                Log.d("sendRegistrationIdTo", "" + regId);
              /*  if (igcmToken != null) {
                    igcmToken.receivedToken();
                }*/
            }
        };
//        }
    }

    public String getFCMRegId() {
        String gcmID = "";
        if (mFcmRegister != null && mFcmRegister.getRegistrationId(this) != null) {
            gcmID = mFcmRegister.getRegistrationId(this);
            Log.d("Token",gcmID);
        }
        return gcmID;
    }
}
