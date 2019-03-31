package com.iteso.mitutor.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Subject implements Parcelable {

    private String subjectName;
    private String subjectCode;
    private String tutorName;
    private String tutorPhone;
    private String tutorDescription;
    private String tuitionLocation;
    private String subjectImageUrl;

    public String getTutorDescription() {
        return tutorDescription;
    }

    public void setTutorDescription(String tutorDescription) {
        this.tutorDescription = tutorDescription;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    private String score;
    private int id;


    public String getSubjectImageUrl() {
        return subjectImageUrl;
    }

    public void setSubjectImageUrl(String subjectImageUrl) {
        this.subjectImageUrl = subjectImageUrl;
    }

    public int getId() {
        return id;
    }

    public String getTuitionLocation() {
        return tuitionLocation;
    }

    public void setTuitionLocation(String tuitionLocation) {
        this.tuitionLocation = tuitionLocation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTutorPhone() {
        return tutorPhone;
    }

    public void setTutorPhone(String tutorPhone) {
        this.tutorPhone = tutorPhone;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Subject (Parcel in){
        this.subjectName = in.readString();
        this.subjectCode = in.readString();
        this.tutorName = in.readString();
        this.tutorPhone = in.readString();
        this.tuitionLocation = in.readString();
        this.subjectImageUrl = in.readString();
        this.id = in.readInt();
    }

    public Subject(){}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.subjectName);
        dest.writeString(this.subjectCode);
        dest.writeString(this.tutorName);
        dest.writeString(this.tutorPhone);
        dest.writeString(this.tuitionLocation);
        dest.writeString(this.subjectImageUrl);
        dest.writeInt(this.id);
    }

    public static final Creator<Subject> CREATOR = new Creator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel source) {
            return new Subject(source);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };
}
