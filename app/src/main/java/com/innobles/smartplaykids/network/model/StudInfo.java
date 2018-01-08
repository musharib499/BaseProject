
package com.innobles.smartplaykids.network.model;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class StudInfo implements Parcelable
{

    private String studentID;
    private String studentName;
    private String studentImage;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<StudInfo> CREATOR = new Creator<StudInfo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public StudInfo createFromParcel(Parcel in) {
            return new StudInfo(in);
        }

        public StudInfo[] newArray(int size) {
            return (new StudInfo[size]);
        }

    }
    ;

    protected StudInfo(Parcel in) {
        this.studentID = ((String) in.readValue((String.class.getClassLoader())));
        this.studentName = ((String) in.readValue((String.class.getClassLoader())));
        this.studentImage = ((String) in.readValue((String.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    public StudInfo() {
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(String studentImage) {
        this.studentImage = studentImage;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(studentID);
        dest.writeValue(studentName);
        dest.writeValue(studentImage);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
