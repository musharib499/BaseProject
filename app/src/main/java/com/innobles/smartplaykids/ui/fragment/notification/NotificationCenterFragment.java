package com.innobles.smartplaykids.ui.fragment.notification;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innobles.smartplaykids.R;
import com.innobles.smartplaykids.ui.base.BaseFragment;
import com.innobles.smartplaykids.ui.fragment.kidslist.KidsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationCenterFragment extends BaseFragment {


    private NotificationPagerAdapter mPagerAdapter;

    @BindView(R.id.view_pager_notification)
    ViewPager mViewPager;


    public static NotificationCenterFragment newInstance() {

        return new NotificationCenterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification_center, container, false);
        setUnBinder(ButterKnife.bind(this, v));
        setUp(v);
        onClickAction();
        return v;
        // Inflate the layout for this fragment
    }

    @Override
    protected void setUp(View view) {
        mPagerAdapter = new NotificationPagerAdapter(getActivity(),getActivity().getSupportFragmentManager());
        mPagerAdapter.setCount(3);

        mViewPager.setAdapter(mPagerAdapter);
        onTabLayout().setVisibility(View.VISIBLE);

       onTabLayout().setupWithViewPager(mViewPager);

    }

    @Override
    public void onClick(View view) {

    }
}
