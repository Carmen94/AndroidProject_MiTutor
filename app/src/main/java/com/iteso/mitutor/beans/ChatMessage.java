package com.iteso.mitutor.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ChatMessage implements Parcelable {
    private User author;
    private long date;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public ChatMessage(User author, String message){
        this.author=author;
        this.text=message;
        this.date = new Date().getTime();
    }

    public ChatMessage(){};


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.author, flags);
        dest.writeLong(this.date);
        dest.writeString(this.text);
    }

    protected ChatMessage(Parcel in) {
        this.author = in.readParcelable(User.class.getClassLoader());
        this.date = in.readLong();
        this.text = in.readString();
    }

    public static final Parcelable.Creator<ChatMessage> CREATOR = new Parcelable.Creator<ChatMessage>() {
        @Override
        public ChatMessage createFromParcel(Parcel source) {
            return new ChatMessage(source);
        }

        @Override
        public ChatMessage[] newArray(int size) {
            return new ChatMessage[size];
        }
    };
}
