package com.malaria.models;

import androidx.annotation.Keep;

import java.io.Serializable;
@Keep
public class Treatment implements Serializable {
    private String title,description,uid;

    public Treatment() {
    }

    public Treatment(String title, String description, String uid) {
        this.title = title;
        this.description = description;
        this.uid = uid;
    }

    public Treatment(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
