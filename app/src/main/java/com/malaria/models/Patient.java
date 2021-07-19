package com.malaria.models;

import androidx.annotation.Keep;

import java.io.Serializable;
@Keep
public class Patient implements Serializable {
    private String first_name,last_name,middle_name,chills,fatigue,flu,fever,meds,
            diarrhea,have_net,location,medication,rhw_id,sweats,sleep_disturbance,
            treatment_regularity,yellow_skin,other,dob,created_at;

    public Patient() {
    }

    public Patient(String first_name, String last_name, String middle_name,
                   String chills, String fatigue, String flu, String fever,
                   String meds, String diarrhea, String have_net, String location,
                   String medication, String rhw_id, String sweats, String sleep_disturbance,
                   String treatment_regularity, String yellow_skin, String other, String dob, String created_at) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.chills = chills;
        this.fatigue = fatigue;
        this.flu = flu;
        this.fever = fever;
        this.meds = meds;
        this.diarrhea = diarrhea;
        this.have_net = have_net;
        this.location = location;
        this.medication = medication;
        this.rhw_id = rhw_id;
        this.sweats = sweats;
        this.sleep_disturbance = sleep_disturbance;
        this.treatment_regularity = treatment_regularity;
        this.yellow_skin = yellow_skin;
        this.other = other;
        this.dob = dob;
        this.created_at = created_at;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getChills() {
        return chills;
    }

    public void setChills(String chills) {
        this.chills = chills;
    }

    public String getFatigue() {
        return fatigue;
    }

    public void setFatigue(String fatigue) {
        this.fatigue = fatigue;
    }

    public String getFlu() {
        return flu;
    }

    public void setFlu(String flu) {
        this.flu = flu;
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getMeds() {
        return meds;
    }

    public void setMeds(String meds) {
        this.meds = meds;
    }

    public String getDiarrhea() {
        return diarrhea;
    }

    public void setDiarrhea(String diarrhea) {
        this.diarrhea = diarrhea;
    }

    public String getHave_net() {
        return have_net;
    }

    public void setHave_net(String have_net) {
        this.have_net = have_net;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getRhw_id() {
        return rhw_id;
    }

    public void setRhw_id(String rhw_id) {
        this.rhw_id = rhw_id;
    }

    public String getSweats() {
        return sweats;
    }

    public void setSweats(String sweats) {
        this.sweats = sweats;
    }

    public String getSleep_disturbance() {
        return sleep_disturbance;
    }

    public void setSleep_disturbance(String sleep_disturbance) {
        this.sleep_disturbance = sleep_disturbance;
    }

    public String getTreatment_regularity() {
        return treatment_regularity;
    }

    public void setTreatment_regularity(String treatment_regularity) {
        this.treatment_regularity = treatment_regularity;
    }

    public String getYellow_skin() {
        return yellow_skin;
    }

    public void setYellow_skin(String yellow_skin) {
        this.yellow_skin = yellow_skin;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
