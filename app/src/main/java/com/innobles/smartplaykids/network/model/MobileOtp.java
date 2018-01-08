
package com.innobles.smartplaykids.network.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MobileOtp extends BaseResponseModel implements Parcelable
{
    private String mobile;
    public final static Creator<MobileOtp> CREATOR = new Creator<MobileOtp>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MobileOtp createFromParcel(Parcel in) {
            MobileOtp instance = new MobileOtp();
            instance.mobile = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public MobileOtp[] newArray(int size) {
            return (new MobileOtp[size]);
        }

    }
    ;



    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mobile);
    }

    public int describeContents() {
        return  0;
    }
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
