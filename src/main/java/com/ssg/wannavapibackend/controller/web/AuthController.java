package com.ssg.wannavapibackend.controller.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssg.wannavapibackend.domain.Token;
import com.ssg.wannavapibackend.dto.response.KakaoResponseDTO;
import com.ssg.wannavapibackend.security.util.JWTUtil;
import com.ssg.wannavapibackend.service.KakaoService;
import com.ssg.wannavapibackend.service.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final KakaoService kakaoService;

    private final TokenService tokenService;

    private final JWTUtil jwtUtil;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("location", kakaoService.getKakaoLogin());
        return "/auth/login";
    }

    @GetMapping("/callback")
    public String callback(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        KakaoResponseDTO kakaoResponseDTO = kakaoService.getKakaoInfo(request.getParameter("code"));

        Long userId = kakaoResponseDTO.getId();
        Map<String, Object> dataMap = kakaoResponseDTO.getDataMap();

        String accessToken = jwtUtil.createToken(dataMap, 60);
        String refreshToken = jwtUtil.createToken(Map.of("mid", userId), 60 * 60);

        Token token = new Token(userId, accessToken, refreshToken);

        tokenService.saveToken(token);

        log.info("accessToken: " + accessToken);
        log.info("refreshToken: " + refreshToken);

        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setMaxAge(60 * 60 * 60 * 24);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setPath("/");

        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        accessTokenCookie.setMaxAge(60 * 60 * 60 * 24);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");

        response.addCookie(refreshTokenCookie);

        return "redirect:/restaurant/";
    }
}