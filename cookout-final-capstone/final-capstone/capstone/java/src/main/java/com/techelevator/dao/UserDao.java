package com.techelevator.dao;

import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(int userId);

    User getUserByEmail (String emailAddress);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role, String emailAddress, String userNotes);

    int update(User user, int id);
}
