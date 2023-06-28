package com.techelevator.controller;

import com.techelevator.dao.UserCookoutsDao;
import com.techelevator.model.UserCookout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/userCookout")
public class UserCookoutController {

    public UserCookoutController(UserCookoutsDao userCookoutsDao){
        this.userCookoutsDao = userCookoutsDao;
    }

    @Autowired
    private UserCookoutsDao userCookoutsDao;

    // 200
    @GetMapping("/cookoutDetail/{cookoutId}")
    public List<UserCookout> getGuestListCookoutById(@PathVariable int cookoutId){
        List<UserCookout> userCookout = userCookoutsDao.getGuestListCookoutById(cookoutId);
            return userCookout;
        }

    @GetMapping("/cookoutDetail/{cookoutId}/{id}")
    public UserCookout getUserCookoutById(@PathVariable int cookoutId, @PathVariable int id){
        return userCookoutsDao.getUserCookoutById(cookoutId, id);
    }

// TODO
//    // 200, empty array, needs data
//    @GetMapping("/{cookoutId}/users")
//    public List<UserCookout> getUsersByCookoutId(@PathVariable int cookoutId){
//        List<UserCookout> usersByCookoutId = userCookoutsDao.getUsersByCookoutId(cookoutId);
//        if (usersByCookoutId == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users not found.");
//        } else {
//            return usersByCookoutId;
//        }
//    }

    // 200 -ok
    @PostMapping("newUserCookout")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createUserCookout(@RequestBody UserCookout userCookout) {
        return userCookoutsDao.createUserCookout(userCookout, userCookout.getCookoutId());
    }

    // 200 - ok
    @PutMapping("/update/{cookoutId}")
    public UserCookout updateUserCookout(@RequestBody UserCookout cookout, @PathVariable int cookoutId){
        UserCookout updatedCookout = userCookoutsDao.updateUserCookout(cookout, cookoutId);
        if(updatedCookout == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cookout not found.");
        }
        return updatedCookout;
    }

    @PutMapping("/update/{cookoutId}/{userId}")
    public UserCookout updateUserCookoutRSVP(@RequestBody UserCookout userCookout, @PathVariable int cookoutId, @PathVariable int userId){
        boolean rsvp;
        if(userCookout.getRsvp()) {
            rsvp = false;
        } else {
            rsvp = true;
        }
        UserCookout updatedCookout = userCookoutsDao.updateUserCookoutRSVP(userCookout, userId, rsvp, cookoutId);
        if(updatedCookout == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cookout not found.");
        }
        return updatedCookout;
    }

}
