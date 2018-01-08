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

package com.innobles.smartplaykids.ui.fragment.notification;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.innobles.smartplaykids.R;
import com.innobles.smartplaykids.ui.fragment.alert.AlertFragment;
import com.innobles.smartplaykids.ui.fragment.attendance.AttendanceFragment;
import com.innobles.smartplaykids.ui.fragment.cirular.CircularFragment;
import com.innobles.smartplaykids.ui.fragment.kidslist.KidsFragment;


/**
 * Created by Mushareb Ali on 06/01/2017.
 */

public class NotificationPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;
    private Context mContext;
    private String tabTitles[] =null;

    public NotificationPagerAdapter(Context context,FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mContext=context;
        this.mTabCount = 0;
       this.tabTitles= mContext.getResources().getStringArray(R.array.tab_array);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return AttendanceFragment.newInstance();
            case 1:
               return CircularFragment.newInstance();
            case 2:
                return AlertFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
