package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Cookouts {

    private int cookoutId;
    private String cookoutName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(pattern="HH:mm")
    private LocalTime time;
    private String location;
    private String cookoutRole;
    private String userNotes;

    public Cookouts() { }

    public Cookouts(int cookoutId, String cookoutName, LocalDate date, LocalTime time, String location) {
        this.cookoutId = cookoutId;
        this.cookoutName = cookoutName;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public Cookouts(int cookoutId, String cookoutName, LocalDate date, LocalTime time, String location, String cookout_role, String userNotes) {
        this.cookoutId = cookoutId;
        this.cookoutName = cookoutName;
        this.date = date;
        this.time = time;
        this.location = location;
        this.cookoutRole = cookout_role;
        this.userNotes = userNotes;
    }

    //added for Mycookouts
    public Cookouts(String cookoutName, LocalDate date, LocalTime time, String location, String cookout_role, String userNotes) {
        this.cookoutName = cookoutName;
        this.date = date;
        this.time = time;
        this.location = location;
        this.cookoutRole = cookout_role;
        this.userNotes = userNotes;
    }
    //role and usernotes added for Mycookouts

    public String getCookoutRole() {
        return cookoutRole;
    }

    public void setCookoutRole(String cookoutRole) {
        this.cookoutRole = cookoutRole;
    }

    public String getUserNotes() {
        return userNotes;
    }

    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getCookoutId() {
        return cookoutId;
    }

    public String getCookoutName() {
        return cookoutName;
    }

    public void setCookoutName(String cookoutName) {
        this.cookoutName = cookoutName;
    }

    public void setCookoutId(int cookoutId) {
        this.cookoutId = cookoutId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Cookouts{" +
                "cookoutId=" + cookoutId +
                ", cookoutName='" + cookoutName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
