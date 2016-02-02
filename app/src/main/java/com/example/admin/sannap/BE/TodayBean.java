package com.example.admin.sannap.BE;

import java.io.Serializable;

/**
 * Created by appslure on 20-01-2016.
 */
public class TodayBean implements Serializable {

    private String spotting,cervical,temperature,ovulation,body,mood,notes,sexual,pill,userID,cycleID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCycleID() {
        return cycleID;
    }

    public void setCycleID(String cycleID) {
        this.cycleID = cycleID;
    }

    public String getSpotting() {
        return spotting;
    }

    public void setSpotting(String spotting) {
        this.spotting = spotting;
    }

    public String getCervical() {
        return cervical;
    }

    public void setCervical(String cervical) {
        this.cervical = cervical;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getOvulation() {
        return ovulation;
    }

    public void setOvulation(String ovulation) {
        this.ovulation = ovulation;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSexual() {
        return sexual;
    }

    public void setSexual(String sexual) {
        this.sexual = sexual;
    }

    public String getPill() {
        return pill;
    }

    public void setPill(String pill) {
        this.pill = pill;
    }
}
