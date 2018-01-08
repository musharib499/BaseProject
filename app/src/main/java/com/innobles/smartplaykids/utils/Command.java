package com.innobles.smartplaykids.utils;

/**
 * Created by Mushareb Ali on 05,January,2018.
 * ali.musharib1@gmail.com
 */

public interface Command {
    int SETUP_ACCOUNT = 0x1000;
    int LOGIN_EVENT = SETUP_ACCOUNT + 1;
    int MOBILE_OTP=LOGIN_EVENT+1;
    int KIDS_INFO=MOBILE_OTP+1;
    int DASH_BOARD=KIDS_INFO+1;
    int NOTIFICATION_CENTER=DASH_BOARD+1;
}
