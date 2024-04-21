package com.letsbook.hbs.service;

import com.letsbook.hbs.model.User;


public interface UserService {
    User registerUser(User user);
    User findByUserName(String username);
    User authenticateUser(String username, String password);
}
