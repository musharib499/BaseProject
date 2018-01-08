
package com.innobles.smartplaykids.network.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Login extends BaseResponseModel implements Parcelable
{

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    private String UserID;
    private String mobileNumber;
    public final static Creator<Login> CREATOR = new Creator<Login>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Login createFromParcel(Parcel in) {
            Login instance = new Login();
            instance.UserID = ((String) in.readValue((String.class.getClassLoader())));
            instance.UserID = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Login[] newArray(int size) {
            return (new Login[size]);
        }

    };


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(UserID);
    }

    public int describeContents() {
        return  0;
    }

}
