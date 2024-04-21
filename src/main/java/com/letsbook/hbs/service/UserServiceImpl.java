package com.letsbook.hbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsbook.hbs.model.User;
import com.letsbook.hbs.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}
    @Override
    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUserName(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; 
    }
}
