package com.innobles.smartplaykids.ui.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.innobles.smartplaykids.R;
import com.innobles.smartplaykids.network.model.Login;
import com.innobles.smartplaykids.ui.base.MainBaseActivity;
import com.innobles.smartplaykids.ui.base.MyAccount;
import com.innobles.smartplaykids.utils.Command;

public class SplashActivity extends MainBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Login login= getSmartApplication().getLoginResponse();

            if (login!=null &&login.getStatus()) {
                Intent intent = new Intent(SplashActivity.this, MyAccount.class);
                intent.putExtra("TAG", Command.DASH_BOARD);
                startActivity(intent);
            }else {
                Intent intent = new Intent(SplashActivity.this, MyAccount.class);
                intent.putExtra("TAG", Command.LOGIN_EVENT);
                startActivity(intent);
            }


    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
