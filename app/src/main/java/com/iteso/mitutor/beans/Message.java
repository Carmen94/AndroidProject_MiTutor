package com.iteso.mitutor.beans;

import java.util.Date;

public class Message {

    private String message;
    private String author;
    private long time;

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
}
