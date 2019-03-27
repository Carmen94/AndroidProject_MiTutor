package com.iteso.mitutor.beans;

public class Subject {

    private String subjectName;
    private String subjectCode;
    private String tutorName;
    private String tutorPhone;
    private String tutionLocation;
    private String subjectImageUrl;
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

    public String getTutionLocation() {
        return tutionLocation;
    }

    public void setTutionLocation(String tutionLocation) {
        this.tutionLocation = tutionLocation;
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
}
