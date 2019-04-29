package com.iteso.mitutor.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Chat implements Parcelable {
    private String chatKey;
    private User user;
    private Tutor tutor;

    public String getChatKey() {
        return chatKey;
    }

    private void setChatKey(String chatKey) {
        this.chatKey = chatKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.chatKey);
        dest.writeParcelable((Parcelable) this.user, flags);
        dest.writeParcelable((Parcelable) this.tutor, flags);
    }

    public Chat() {
    }

    protected Chat(Parcel in) {
        this.chatKey = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.tutor = in.readParcelable(Tutor.class.getClassLoader());
    }

    public static final Parcelable.Creator<Chat> CREATOR = new Parcelable.Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel source) {
            return new Chat(source);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };

    public Chat(User user, Tutor tutor) {
        this.user = user;
        this.tutor = tutor;
        this.chatKey = user.getUserId()+"_"+tutor.getId();
    }
}
