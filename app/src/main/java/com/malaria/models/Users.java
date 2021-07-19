package com.malaria.models;

public class Users {
    private String name;
    private String email;
    private String address;
    private String experience;
    private String hospital;
    private String location;
    private String specialization;
    private String uid;

    public Users() {
    }

    public Users(String name, String email, String address, String experience, String hospital, String location, String specialization, String uid) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.experience = experience;
        this.hospital = hospital;
        this.location = location;
        this.specialization = specialization;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
