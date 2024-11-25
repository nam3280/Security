package com.ssg.wannavapibackend.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@Getter
//@RedisHash(value = "jwtToken", timeToLive = 60*60*24*3)
@NoArgsConstructor
@RedisHash(value = "jwtToken", timeToLive = 60*3)
public class Token {

    @Id
    private Long id;

    private String accessToken;

    private String refreshToken;
}
