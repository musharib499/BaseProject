package com.innobles.smartplaykids.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.innobles.smartplaykids.BuildConfig;
import com.innobles.smartplaykids.R;

public class MainBaseNavigationActivity extends BaseActivity implements MainBaseNavigationMvpView {
    Toolbar mToolbar;
    DrawerLayout mDrawer;
    NavigationView mNavigationView;
    TextView mAppVersionTextView;
    FrameLayout frameMainLayout;
    LinearLayout frameErrorView;
    TextView tv_error_message;
    Button btn_retry;
    BottomNavigationView bottomNavigationView;
    private TextView mNameTextView;
    private TextView mEmailTextView;
    private ActionBarDrawerToggle mDrawerToggle;

    TabLayout mTabLayout;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_base_navigation);
        init();
        setUp();
        onClickAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }


    private void init() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawer = findViewById(R.id.drawer_view);
        mTabLayout=findViewById(R.id.tab_layout);
        mNavigationView = findViewById(R.id.navigation_view);
        mAppVersionTextView = findViewById(R.id.tv_app_version);
        frameMainLayout = findViewById(R.id.fbaseLayoutMain);
        frameErrorView = findViewById(R.id.lLayoutErrorView);
        tv_error_message = findViewById(R.id.tv_message);
        btn_retry = findViewById(R.id.btn_retry);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);


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
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void setTitleMessage(String message) {
        mToolbar.setTitle(message);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    public void onShowProgressBar() {

    }

 /*navigation menu*/

    @Override
    public FrameLayout onMainFrameLayout() {
        frameErrorView.setVisibility(View.GONE);
        frameMainLayout.setVisibility(View.VISIBLE);

        return frameMainLayout;
    }

    @Override
    protected void setUp() {
        setTitleMessage("Main View");
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                onHideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setupNavMenu();
        updateAppVersion();
    }

    @Override
    public void onBottomNavigationShow() {
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBottomNavigationHide() {
        bottomNavigationView.setVisibility(View.GONE);
    }


    void setupNavMenu() {
        View headerLayout = mNavigationView.getHeaderView(0);
        mNameTextView = (TextView) headerLayout.findViewById(R.id.tv_name);
        mEmailTextView = (TextView) headerLayout.findViewById(R.id.tv_email);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        mDrawer.closeDrawer(GravityCompat.START);
                        switch (item.getItemId()) {
                            case R.id.nav_item_about:
                                // mPresenter.onDrawerOptionAboutClick();
                                return true;
                            case R.id.nav_item_rate_us:
                                // mPresenter.onDrawerRateUsClick();
                                return true;
                            case R.id.nav_item_feed:
                                //  mPresenter.onDrawerMyFeedClick();
                                return true;
                            case R.id.nav_item_logout:
                                //  mPresenter.onDrawerOptionLogoutClick();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }


    @Override
    public void closeNavigationDrawer() {
        if (mDrawer != null) {
            mDrawer.closeDrawer(Gravity.START);
        }
    }

    @Override
    public void lockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    /*Bottom menu*/

    /*user info*/

    @Override
    public void updateUserName(String currentUserName) {
        mNameTextView.setText(currentUserName);
    }

    @Override
    public void updateUserEmail(String currentUserEmail) {
        mEmailTextView.setText(currentUserEmail);
    }

    @Override
    public void updateAppVersion() {
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        mAppVersionTextView.setText(version);
    }

    @Override
    public void updateUserProfilePic(String currentUserProfilePicUrl) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {

    }
}
