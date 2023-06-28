package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {

   private int userId;
   private String username;
   @JsonIgnore
   private String password;
   @JsonIgnore
   private boolean activated;
   private String emailAddress;
   private String userNotes;
   private Set<Authority> authorities = new HashSet<>();

   public User() { }

   public User(int id, String username, String password, String authorities) {
      this.userId = userId;
      this.username = username;
      this.password = password;
      if(authorities != null) this.setAuthorities(authorities);
      this.activated = true;
   }

   public User(int id, String username, String password, boolean activated, String emailAddress, String userNotes, Set<Authority> authorities) {
      this.userId = userId;
      this.username = username;
      this.password = password;
      this.activated = activated;
      this.emailAddress = emailAddress;
      this.userNotes = userNotes;
      this.authorities = authorities;
   }

   public User(Set<Authority> authorities) {
      this.authorities = authorities;
   }

   public User(String username, String role, String emailAddress, String userNotes) {
      this.username = username;
      this.emailAddress = emailAddress;
      this.userNotes = userNotes;
   }

   public int getId() {
      return userId;
   }

   public void setId(int userId) {
      this.userId = userId;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public boolean isActivated() {
      return activated;
   }

   public void setActivated(boolean activated) {
      this.activated = activated;
   }

   public Set<Authority> getAuthorities() {
      return authorities;
   }

   public void setAuthorities(Set<Authority> authorities) {
      this.authorities = authorities;
   }

   public void setAuthorities(String authorities) {
      String[] roles = authorities.split(",");
      for(String role : roles) {
         String authority = role.contains("ROLE_") ? role : "ROLE_" + role;
         this.authorities.add(new Authority(authority));
      }
   }

   public String getEmailAddress() {
      return emailAddress;
   }

   public void setEmailAddress(String emailAddress) {
      this.emailAddress = emailAddress;
   }

   public String getUserNotes() {
      return userNotes;
   }

   public void setUserNotes(String userNotes) {
      this.userNotes = userNotes;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return userId == user.userId &&
              activated == user.activated &&
              Objects.equals(username, user.username) &&
              Objects.equals(password, user.password) &&
              Objects.equals(authorities, user.authorities);
   }

   @Override
   public int hashCode() {
      return Objects.hash(userId, username, password, activated, authorities);
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + userId +
              ", username='" + username + '\'' +
              ", activated=" + activated +
              ", authorities=" + authorities +
              '}';
   }
}
