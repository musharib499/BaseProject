package com.innobles.smartplaykids.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.innobles.smartplaykids.R;
import com.innobles.smartplaykids.ui.dashboard.DashBoardActivity;
import com.innobles.smartplaykids.ui.fragment.kidslist.KidsFragment;
import com.innobles.smartplaykids.ui.fragment.login.LoginFragment;
import com.innobles.smartplaykids.ui.fragment.notification.NotificationCenterFragment;
import com.innobles.smartplaykids.ui.fragment.otp.MobileOtpFragmant;
import com.innobles.smartplaykids.utils.Command;

/**
 * Created by Mushareb Ali on 05,January,2018.
 * ali.musharib1@gmail.com
 */

public class MyAccount extends MainBaseActivity {
    private int TAG;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //toolbar_account.setNavigationIcon(mCars24Application.getResources().getDrawable(R.drawable.toolbar_back));
        mToolbar.setTitle(R.string.my_account);
        mToolbar.setNavigationContentDescription("BACK");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        intent = getIntent();
        TAG = intent.getIntExtra("TAG", 0);
        populateFragment(TAG);
    }


    private void populateFragment(int TAG) {
        // Open Change Password Page
        switch (TAG) {
            case Command.LOGIN_EVENT:
                setTitleMessage(getString(R.string.login));
                replaceFragment(LoginFragment.newInstance(), null, false);
                break;
            case Command.MOBILE_OTP:
                setTitleMessage(getString(R.string.login));
                replaceFragment(MobileOtpFragmant.newInstance(), intent.getExtras(), false);
                break;
            case Command.KIDS_INFO:
                setTitleMessage(getString(R.string.login));
                replaceFragment(KidsFragment.newInstance(), intent.getExtras(), false);
                break;
            case Command.NOTIFICATION_CENTER:
                setTitleMessage(getString(R.string.login));
                replaceFragment(NotificationCenterFragment.newInstance(), intent.getExtras(), false);
                break;

            case Command.DASH_BOARD:
                startActivity(new Intent(MyAccount.this, DashBoardActivity.class));
                finish();
                break;

        }
    }
}
