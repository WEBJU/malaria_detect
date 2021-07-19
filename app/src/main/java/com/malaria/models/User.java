package com.malaria.models;

public class User {
    String displayName;
    String Email;
    long createdAt;

    public User(String displayName, String email, long createdAt) {
        this.displayName = displayName;
        Email = email;
        this.createdAt = createdAt;
    }

    public User() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
