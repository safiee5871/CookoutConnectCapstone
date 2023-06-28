package com.techelevator.dao;

import com.techelevator.model.Cookouts;

import java.util.List;

public interface CookoutsDao {

    // get cookout details by cookoutId
    Cookouts getCookoutDetailsById(int cookoutId);

    // create cookout
    Cookouts createCookout(Cookouts cookouts, int cookoutId);

    // update cookout
    Cookouts updateCookout(Cookouts cookouts, int cookoutId);

    //get cookouts by user id
    List<Cookouts> getCookoutsByUserId(int userId);

//    // delete cookout
//    void deleteCookout(int cookoutId);
}
