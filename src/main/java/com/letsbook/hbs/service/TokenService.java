package com.letsbook.hbs.service;

import com.letsbook.hbs.model.Token;

public interface TokenService {
    Token saveToken(Token token);
    Token getTokenByUserId(Long userId);
}
