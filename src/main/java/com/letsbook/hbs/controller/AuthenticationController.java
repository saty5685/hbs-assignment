package com.letsbook.hbs.controller;
import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.letsbook.hbs.dto.LoginRequest;
import com.letsbook.hbs.model.User;
import com.letsbook.hbs.service.UserService;
import com.letsbook.hbs.utils.JwtTokenUtil;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private  JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            String token = jwtTokenUtil.generateJwtToken(user);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            return  ResponseEntity.ok().headers(headers).body("Login Success");
        } else {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
