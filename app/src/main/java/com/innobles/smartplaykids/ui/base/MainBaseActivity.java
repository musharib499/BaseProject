package com.innobles.smartplaykids.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.innobles.smartplaykids.R;

public class MainBaseActivity extends BaseActivity {
    Toolbar mToolbar;
    FrameLayout frameMainLayout;
    LinearLayout frameErrorView;
    TextView tv_error_message;
    Button btn_retry;
    BottomNavigationView bottomNavigationView;
    TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appbar_layout);
        init();
    }

    @Override
    protected void setUp() {
    }

    @Override
    public void onBackArrowShow() {
        super.onBackArrowShow();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void init() {
        mToolbar = findViewById(R.id.toolbar);
        frameMainLayout = findViewById(R.id.fbaseLayoutMain);
        frameErrorView = findViewById(R.id.lLayoutErrorView);
        mTabLayout=findViewById(R.id.tab_layout);
        tv_error_message = findViewById(R.id.tv_message);
        btn_retry = findViewById(R.id.btn_retry);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    public TabLayout onTabLayout() {
        return mTabLayout;
    }

    @Override
    public void onErrorFullScreen(String message) {
        if (!TextUtils.isEmpty(message)) {
            frameMainLayout.setVisibility(View.GONE);
            frameErrorView.setVisibility(View.VISIBLE);
            tv_error_message.setText(message);


        }
    }

    @Override
    public void onClickAction() {

    }
    @Override
    public void setTitleMessage(String message) {
        mToolbar.setTitle(message);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onBottomNavigationShow() {
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBottomNavigationHide() {
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public Button onButtonRetry() {
        onMainFrameLayout();
        return btn_retry;
    }

    @Override
    public void onLoadedSuccess(Object o) {

    }

    @Override
    public void onLoadedFailure(Throwable error) {

    }

    @Override
    public void onShowProgressBar() {

    }

    @Override
    public FrameLayout onMainFrameLayout() {
        frameErrorView.setVisibility(View.GONE);
        frameMainLayout.setVisibility(View.VISIBLE);

        return frameMainLayout;
    }
    /*Bottom navigation*/
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   /* mTextMessage.setText(R.string.title_home);*/
                    return true;
                case R.id.navigation_dashboard:
                  /*  mTextMessage.setText(R.string.title_dashboard);*/
                    return true;
                case R.id.navigation_notifications:
                   /* mTextMessage.setText(R.string.title_notifications);*/
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onClick(View view) {

    }

}
