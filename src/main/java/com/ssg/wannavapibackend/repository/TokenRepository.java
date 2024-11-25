package com.ssg.wannavapibackend.repository;

import com.ssg.wannavapibackend.domain.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {
    Token findByAccessToken(String accessToken);
}
