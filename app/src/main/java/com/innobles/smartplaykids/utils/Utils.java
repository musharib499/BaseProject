package com.innobles.smartplaykids.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.innobles.smartplaykids.R;
import com.innobles.smartplaykids.ui.widget.EmptyRecyclerView;

import org.json.JSONObject;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;

/**
 * Created by Musharib Ali on 5/26/2017.
 */

public class Utils {

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void setImageGlide(Context context, String url, CircleImageView imageView) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(R.drawable.ic_rabbit)
                .centerCrop()
                .into(imageView);
    }
    public static String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public static String getNumberWithHideLast(String number)
    {

      return number.length()>4 ? number.substring(0,4)+"XXXXXX" :"";
    }
    public static boolean isNullOrEmpty(final String pStr) {
        return pStr == null || pStr.trim().length() == 0 || pStr.trim().equalsIgnoreCase("null");
    }
    public static void showNoConnectionDialog(final Activity activity) {
        showNoConnectionDialog(activity,activity.getString(R.string.connection_error),activity.getString(R.string.connection_error),null);
    }
    public static void showNoConnectionDialog(final Activity activity, String title, String message, DialogInterface.OnClickListener onClickListener) {

        AlertDialog.Builder builder;// = new AlertDialog.Builder(activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(activity);
        }

        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setTitle(title);

        builder.setPositiveButton("Ok",onClickListener);


        builder.show();
    }
    public static String getDeviceId(Context context) {
        String deviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

       /* if(TextUtils.isEmpty(deviceId)){
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = telephonyManager.getDeviceId();
        }
*/
        return deviceId;
    }

    public static EmptyRecyclerView recyclerView(EmptyRecyclerView recyclerView, Context context, boolean orientation) {
        LinearLayoutManager recycler_layout = new LinearLayoutManager(context);
        if (orientation)
            recycler_layout.setOrientation(LinearLayout.VERTICAL);
        else
            recycler_layout.setOrientation(LinearLayout.HORIZONTAL);

        recyclerView.setLayoutManager(recycler_layout);
      /*  recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());*/
        return recyclerView;
    }

    public static HashMap<String,String>  hashParam()
    {
        HashMap<String, String> params = new HashMap<>();

        return params;
    }

}
