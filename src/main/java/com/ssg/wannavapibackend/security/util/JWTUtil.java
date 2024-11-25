package com.ssg.wannavapibackend.security.util;

import com.ssg.wannavapibackend.domain.Token;
import com.ssg.wannavapibackend.dto.response.KakaoResponseDTO;
import com.ssg.wannavapibackend.service.TokenService;
import com.ssg.wannavapibackend.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import static org.springframework.web.util.WebUtils.getCookie;

@Component
@Log4j2
@RequiredArgsConstructor
public class JWTUtil {

    private final TokenService tokenService;

    private final UserService userService;

    private static final String key = "1234567890123456789012345678901234567890";
    /**
     * JWT 토큰 생성 메서드
     *
     * @param valueMap 토큰에 포함할 클레임 정보 (예: 이메일, 권한 등)
     * @param min      토큰의 만료 시간 (분 단위)
     * @return 생성된 JWT 토큰 문자열
     */
    public String createToken(Map<String, Object> valueMap, int min) {
        SecretKey key = null;

        try {
            // **암호화 키 생성**: `key` 문자열을 HMAC-SHA256 알고리즘에 맞게 SecretKey로 변환
            key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            // 키 변환 실패 시 예외 처리
            throw new RuntimeException(e.getMessage());
        }

        // **JWT 토큰 빌더**
        return Jwts.builder()
                .header() // 헤더 설정 시작
                .add("typ", "JWT") // JWT 타입 명시
                .add("alg", "HS256") // 서명 알고리즘 명시
                .and() // 헤더 설정 종료
                .issuedAt(Date.from(ZonedDateTime.now().toInstant())) // 발행 시간 설정
                .expiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant())) // 만료 시간 설정
                .claims(valueMap) // 클레임 추가 (사용자 정보)
                .signWith(key) // 키를 사용하여 서명
                .compact(); // 토큰 생성 및 문자열 반환
    }

    /**
     * JWT 토큰 검증 메서드
     *
     * @param token 클라이언트로부터 받은 JWT 토큰
     * @return 클레임 정보 (JWT에 포함된 사용자 정보와 메타데이터)
     */
    public Map<String, Object> validateToken(String token, HttpServletRequest request, HttpServletResponse response) {
        SecretKey key = null;
        try {
            // **암호화 키 생성**: 토큰 검증을 위해 동일한 키로 SecretKey 생성
            key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            // 키 변환 실패 시 예외 처리
            throw new RuntimeException(e.getMessage());
        }

        Claims claims = verifyToken(token, key);

        log.info("claims: " + claims); // 검증된 클레임을 로그로 출력

        //2. 엑세스 토큰이 만료됬을 때
        if(claims == null) {
            log.info("들어오긴 하냐?" + getCookie(request));
            Token newToken = tokenService.checkToken(getCookie(request));
            log.info("쿠키꺼1" + newToken.getAccessToken());
            log.info("쿠키꺼2" + newToken.getId());
            log.info("쿠키꺼3" + newToken.getRefreshToken());
            if(newToken == null){
                log.info("리프래시 토큰도 없어?");
                throw new BadCredentialsException("로그인이 필요합니다.");
            }

            KakaoResponseDTO kakaoResponseDTO = userService.getUser(newToken.getId());

            String newAccessToken = createToken(kakaoResponseDTO.getDataMap(),1);

            regenerateToken(newAccessToken, response);

            Token createToken = new Token( newToken.getId(), newAccessToken, newToken.getRefreshToken());

            tokenService.saveToken(createToken);
        }

//        Claims claims = Jwts.parser()
//                .verifyWith(key)
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();

        // 클레임 정보를 반환 (예: 이메일, 권한 등)
        return claims;
    }

    public void regenerateToken(String accessToken, HttpServletResponse response){
        Cookie accessTokenCookie = new Cookie("accessToken", null);
        accessTokenCookie.setMaxAge(0);
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);


        accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setMaxAge(-1);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(false);
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);
    }

    private Claims verifyToken(String token, SecretKey key) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    public String getCookie(HttpServletRequest req){
        Cookie[] cookies=req.getCookies(); // 모든 쿠키 가져오기
        if(cookies!=null){
            for (Cookie c : cookies) {
                String name = c.getName(); // 쿠키 이름 가져오기
                String value = c.getValue(); // 쿠키 값 가져오기
                if (name.equals("accessToken")) {
                    return value;
                }
            }
        }
        return null;
    }
}