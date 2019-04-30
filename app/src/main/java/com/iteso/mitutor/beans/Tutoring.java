package com.iteso.mitutor.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Tutoring implements Parcelable {
    private Subject subject;
    private String description;
    private int tutoringId;
    private Tutor tutor;
    private double score;
    private String location;
    private int image;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getTutoringId() {
        return tutoringId;
    }

    public void setTutoringId(int tutoringId) {
        this.tutoringId = tutoringId;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.subject, flags);
        dest.writeString(this.description);
        dest.writeInt(this.tutoringId);
        dest.writeParcelable(this.tutor, flags);
        dest.writeDouble(this.score);
        dest.writeString(this.location);
        dest.writeInt(this.image);
    }

    public Tutoring() {
    }

    protected Tutoring(Parcel in) {
        this.subject = in.readParcelable(Subject.class.getClassLoader());
        this.description = in.readString();
        this.tutoringId = in.readInt();
        this.tutor = in.readParcelable(Tutor.class.getClassLoader());
        this.score = in.readDouble();
        this.location = in.readString();
        this.image = in.readInt();
    }

    public static final Creator<Tutoring> CREATOR = new Creator<Tutoring>() {
        @Override
        public Tutoring createFromParcel(Parcel source) {
            return new Tutoring(source);
        }

        @Override
        public Tutoring[] newArray(int size) {
            return new Tutoring[size];
        }
    };
}
