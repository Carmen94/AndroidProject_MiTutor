package com.iteso.mitutor.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Message implements Parcelable {

    private String message;
    private String author;
    private long time;
    private int chatID;

    public Message(String messageText, String messageUser) {
        this.message = messageText;
        this.author = messageUser;
        time = new Date().getTime();
    }

    public Message(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeString(this.author);
        dest.writeLong(this.time);
        dest.writeInt(this.chatID);
    }

    protected Message(Parcel in) {
        this.message = in.readString();
        this.author = in.readString();
        this.time = in.readLong();
        this.chatID = in.readInt();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };
}
