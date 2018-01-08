package com.innobles.smartplaykids.ui.base;

/**
 * Created by Mushareb Ali on 01,January,2018.
 * ali.musharib1@gmail.com
 */

public interface MainBaseNavigationMvpView extends MvpView {
    void updateUserName(String currentUserName);
    void updateUserEmail(String currentUserEmail);
    void closeNavigationDrawer();
    void lockDrawer();

    void unlockDrawer();
    void updateAppVersion();
    void updateUserProfilePic(String currentUserProfilePicUrl);

}
