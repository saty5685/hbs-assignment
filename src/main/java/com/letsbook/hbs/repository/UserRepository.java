package com.letsbook.hbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letsbook.hbs.model.User;

public interface UserRepository extends JpaRepository<User, Long>  {
    User findByUsername(String username);
    User save(User user); 
}
