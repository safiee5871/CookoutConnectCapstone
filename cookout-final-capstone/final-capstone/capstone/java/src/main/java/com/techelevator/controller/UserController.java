package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserDao userDao;

    // 200 - ok
    @RequestMapping(path = "/myprofile/{id}", method = RequestMethod.PUT)
    public int updateUser(@RequestBody User user, @PathVariable int id) {
        if (user != null) {
            return userDao.update(user, id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot process request");
        }
    }

    // 200 - ok
    @GetMapping(path="/myprofile/{emailAddress}")
    public User getUserByEmail(@PathVariable String emailAddress){
        User userEmail = userDao.getUserByEmail(emailAddress);

        if(userEmail != null){
            return userEmail;
        } else {
            return null;
        }
    }

    // 200
    @GetMapping(path="/myprofile")
    public User myProfile(Principal principal) {
        return userDao.findByUsername(principal.getName());
    }

    // 200
    @GetMapping(path="/users")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }



}
