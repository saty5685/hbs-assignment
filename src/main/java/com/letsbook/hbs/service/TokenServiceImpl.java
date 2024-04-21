package com.letsbook.hbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsbook.hbs.model.Token;
import com.letsbook.hbs.repository.TokenRepository;

@Service
public class TokenServiceImpl implements TokenService{
    
	@Autowired
	TokenRepository tokenRepository;
	
	@Override
	public Token saveToken(Token token) {
		return tokenRepository.save(token);
	}

	@Override
	public Token getTokenByUserId(Long userId) {
		return tokenRepository.findByUserId(userId);
	}

}
