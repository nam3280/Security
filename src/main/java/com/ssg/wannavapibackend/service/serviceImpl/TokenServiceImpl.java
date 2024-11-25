package com.ssg.wannavapibackend.service.serviceImpl;

import com.ssg.wannavapibackend.domain.Token;
import com.ssg.wannavapibackend.repository.TokenRepository;
import com.ssg.wannavapibackend.repository.repositoryImpl.TokenRepositoryImpl;
import com.ssg.wannavapibackend.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    private final TokenRepositoryImpl tokenRepositoryImpl;

    // 토큰 저장
    public void saveToken(Token token) {
        tokenRepository.save(token);
        Token retrievedToken = tokenRepository.findById(token.getId()).orElse(null);
        log.info("저장된 Token 확인: {}", retrievedToken.getAccessToken());
    }

    // 리프레쉬 토큰 조회
    // 쿠키에 존재하는 엑세스 토큰을 통해서 그 유저의 리프레쉬 토큰을 조회할 수 있다.
    // 만료된 엑세스 토큰을 재발급 받을 수 있다.
    // 만약 쿠키에 존재하는 엑세스 토큰으로 리프레쉬 토큰을 조회했을 때 존재하지 않는다면 리프레쉬 토큰은 만료가 되었음을 알 수 있다.
    public Token checkToken(String accessToken) {
        log.info("여긴가"+accessToken);
        Token token = tokenRepository.findByAccessToken(accessToken);
        log.info("들어왔다." + token.getRefreshToken());
        log.info("들어왔다." + token.getAccessToken());
        log.info("들어왔다." + token.getId());
        return token;
    }
}
