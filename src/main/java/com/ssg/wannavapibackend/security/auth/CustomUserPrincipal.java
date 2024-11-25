package com.ssg.wannavapibackend.security.auth;

import lombok.RequiredArgsConstructor;

import java.security.Principal;


// Lombok의 @RequiredArgsConstructor를 사용하여 final 필드의 생성자를 자동 생성
@RequiredArgsConstructor
public class CustomUserPrincipal implements Principal {

    private final String id;

    @Override
    public String getName() {
        return id;
    }
}