package com.ssg.wannavapibackend.service;

import com.ssg.wannavapibackend.domain.Token;

public interface TokenService {
    void saveToken(Token token);

    Token checkToken(String accessToken);
}
