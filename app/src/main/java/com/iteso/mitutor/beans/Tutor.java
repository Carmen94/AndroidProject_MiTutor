package com.iteso.mitutor.beans;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class Tutor implements Parcelable {

    private String firstName;
    private String lastName;
    private String id;
    private boolean active;
    private String phone;
    private String description;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tutor(){};

    public Tutor(String firstName, String lastName, String id, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.active = active;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.id);
        dest.writeByte(this.active ? (byte) 1 : (byte) 0);
        dest.writeString(this.phone);
        dest.writeString(this.description);
    }

    protected Tutor(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.id = in.readString();
        this.active = in.readByte() != 0;
        this.phone = in.readString();
        this.description = in.readString();
    }

    public static final Creator<Tutor> CREATOR = new Creator<Tutor>() {
        @Override
        public Tutor createFromParcel(Parcel source) {
            return new Tutor(source);
        }

        @Override
        public Tutor[] newArray(int size) {
            return new Tutor[size];
        }
    };
}
