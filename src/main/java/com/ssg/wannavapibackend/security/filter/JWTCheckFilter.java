package com.ssg.wannavapibackend.security.filter;

import com.ssg.wannavapibackend.domain.Token;
import com.ssg.wannavapibackend.dto.response.KakaoResponseDTO;
import com.ssg.wannavapibackend.security.auth.CustomUserPrincipal;
import com.ssg.wannavapibackend.security.util.JWTUtil;
import com.ssg.wannavapibackend.service.TokenService;
import com.ssg.wannavapibackend.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.web.util.WebUtils.getCookie;

@Component
@RequiredArgsConstructor
@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    private final TokenService tokenService;

    private final UserService userService;

    /**
     * 필터를 적용하지 않는 부분
     *
     * @param request
     */
    protected boolean shouldNotFilter(HttpServletRequest request) {
        if (request.getServletPath().startsWith("/css/") || request.getServletPath().startsWith("/js/") ||
                request.getServletPath().startsWith("/images/") || request.getServletPath().startsWith("/assets/"))
            return true;
        if (request.getRequestURI().equals("/restaurant/"))
            return true;
        if (request.getServletPath().startsWith("/auth/"))
            return true;

        return false;
    }


    /**
     * 요청과 응답을 할 떄 중간에 껴서 필터를 적용하여 JWT 검증하는 부분
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (shouldNotFilter(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            log.info(jwtUtil.getCookie(request));

            if(jwtUtil.getCookie(request) == null) {
                log.info("로그인을 안했구나!");
                throw new BadCredentialsException("로그인이 필요합니다.");
            }

            Map<String, Object> tokenMap = jwtUtil.validateToken(jwtUtil.getCookie(request), request, response);
            log.info("tokenMap: " + tokenMap);

            String mid = tokenMap.get("id").toString();

            List<GrantedAuthority> authorities = Collections.emptyList();

            if (tokenMap.containsKey("role") && tokenMap.get("role") != null) {
                String[] roles = tokenMap.get("role").toString().split(",");
                authorities = Arrays.stream(roles)
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList());
            }

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            new CustomUserPrincipal(mid),
                            null,
                            authorities
                    );

            // SecurityContext에 인증 객체 저장
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authenticationToken);

            // 다음 필터 실행
            filterChain.doFilter(request, response);

        } catch (Exception e){
            // 인증 예외가 발생하면 로그인 페이지로 리다이렉트
            handleException(response, e);
        }
    }

    // 예외가 발생했을 때 클라이언트에 에러 응답을 보내는 메서드
    private void handleException(HttpServletResponse response, Exception e) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 응답 상태를 403(금지)로 설정
        response.setContentType("application/json"); // 응답 타입을 JSON으로 설정
        response.getWriter().println("{\"error\": \"" + e.getMessage() + "\"}"); // 에러 메시지 JSON 형식으로 반환
        response.sendRedirect("/auth/login");
    }
}
