package com.letsbook.hbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letsbook.hbs.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long>  {
    Token findByUserId(Long userId);
    Token save(Token token);
}
