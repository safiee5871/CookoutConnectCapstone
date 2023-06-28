package com.techelevator.dao;

import com.techelevator.model.UserCookout;

import java.util.List;

public interface UserCookoutsDao {

    List<UserCookout> getGuestListCookoutById(int cookoutId);

//    //See all user id's per cookout
//    List<UserCookout> getUsersByCookoutId(int cookoutId);

    UserCookout getUserCookoutById(int userId, int cookoutId);

    UserCookout updateUserCookoutRSVP(UserCookout userCookout, int id, boolean rsvp, int cookoutId);

    //Returns a list of Cookouts by user id
//    List<UserCookout> getUserCookoutsByUserId(int userId);

    //Create new user cookout
    boolean createUserCookout(UserCookout cookout, int cookoutId);

    //Update user cookout
    UserCookout updateUserCookout(UserCookout cookout, int cookoutId);

}
