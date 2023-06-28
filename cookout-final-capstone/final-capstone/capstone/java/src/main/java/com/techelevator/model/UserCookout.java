package com.techelevator.model;

public class UserCookout {

    private int userId;
    private int cookoutId;
    private String cookoutRole;
    private Boolean rsvp = false;
    private String username;
    

    public UserCookout(){ }

    public UserCookout(int userId, int cookoutId, String cookoutRole, Boolean rsvp) {
        this.userId = userId;
        this.cookoutId = cookoutId;
        this.cookoutRole = cookoutRole;
        this.rsvp = rsvp;
    }

    public UserCookout(int userId, int cookoutId, String cookoutRole, Boolean rsvp, String username) {
        this.userId = userId;
        this.cookoutId = cookoutId;
        this.cookoutRole = cookoutRole;
        this.rsvp = rsvp;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCookoutId() {
        return cookoutId;
    }

    public void setCookoutId(int cookoutId) {
        this.cookoutId = cookoutId;
    }

    public String getCookoutRole() {
        return cookoutRole;
    }

    public void setCookoutRole(String cookoutRole) {
        this.cookoutRole = cookoutRole;
    }

    public Boolean getRsvp() {
        return rsvp;
    }

    public void setRsvp(Boolean rsvp) {
        this.rsvp = rsvp;
    }

    @Override
    public String toString() {
        return "UserCookouts{" +
                "userId=" + userId +
                ", cookoutId=" + cookoutId +
                ", cookoutRole='" + cookoutRole + '\'' +
                ", rsvp=" + rsvp +
                '}';
    }

}
