package com.ssg.wannavapibackend.repository.repositoryImpl;

import com.ssg.wannavapibackend.domain.Token;
import com.ssg.wannavapibackend.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;

@Repository
@RequiredArgsConstructor
@Log4j2
public class TokenRepositoryImpl{

    private final RedisTemplate<String, Object> redisTemplate;

    public Token findByAccessToken(String accessToken) {
        Set<String> keys = redisTemplate.keys("jwtToken:*");

        log.info("찾은 키들: {}", keys);

        for (String key : keys) {
            Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);

            String storedAccessToken = (String) entries.get("accessToken");

            String iid = (String) entries.get("id");
            log.info("id: {}", iid);
            log.info("storedAccessToken: {}", storedAccessToken);

            if (storedAccessToken != null && storedAccessToken.equals(accessToken)) {
                Long id = Long.valueOf((String) entries.get("id"));  // "id"는 String에서 Long으로 변환

                String refreshToken = (String) entries.get("refreshToken");

                return new Token(id, storedAccessToken, refreshToken);
            }
        }

        // 일치하는 토큰이 없을 경우 null 반환
        return null;
    }

}
