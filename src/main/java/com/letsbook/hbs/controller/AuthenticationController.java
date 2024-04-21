package com.letsbook.hbs.controller;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.letsbook.hbs.dto.LoginRequest;
import com.letsbook.hbs.model.Token;
import com.letsbook.hbs.model.User;
import com.letsbook.hbs.service.TokenService;
import com.letsbook.hbs.service.UserService;
import com.letsbook.hbs.utils.JwtTokenUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.TextCodec;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private  JwtTokenUtil jwtTokenUtil;
    @Autowired
    private TokenService tokenService;
    @Value("${jwt.secret}")
    private String secret;
    
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
    	System.out.println(loginRequest.getUserName());
        User user = userService.findByUserName(loginRequest.getUserName());
        System.out.println(user.toString());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            String jwtTokenValue = jwtTokenUtil.generateJwtToken(user);
            // Store token in the database
            Token token = new Token();
            token.setUserId(user.getId());
            token.setTokenValue(jwtTokenValue);
            // Retrieve expiration time from JWT token
            Claims claims = Jwts.parser().setSigningKey(TextCodec.BASE64.decode(secret)).parseClaimsJws(jwtTokenValue).getBody();
            Date expirationDate = claims.getExpiration();
            token.setExpirationDateTime(expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            tokenService.saveToken(token);           
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + jwtTokenValue);
            return  ResponseEntity.ok().headers(headers).body("Login Success");
        } else {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
