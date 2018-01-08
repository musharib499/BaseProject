
package com.innobles.smartplaykids.network.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class StudentInfo extends BaseResponseModel implements Parcelable
{

    private List<StudInfo> studInfo = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<StudentInfo> CREATOR = new Creator<StudentInfo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public StudentInfo createFromParcel(Parcel in) {
            return new StudentInfo(in);
        }

        public StudentInfo[] newArray(int size) {
            return (new StudentInfo[size]);
        }

    }
    ;

    protected StudentInfo(Parcel in) {
        in.readList(this.studInfo, (StudInfo.class.getClassLoader()));
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    public StudentInfo() {
    }

    public List<StudInfo> getStudInfo() {
        return studInfo;
    }

    public void setStudInfo(List<StudInfo> studInfo) {
        this.studInfo = studInfo;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(studInfo);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
