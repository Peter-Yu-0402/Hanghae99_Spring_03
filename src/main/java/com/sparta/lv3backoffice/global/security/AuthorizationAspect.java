package com.sparta.lv3backoffice.global.security;

import com.sparta.lv3backoffice.domain.entity.UserRoleEnum;
import com.sparta.lv3backoffice.global.exception.UnauthorizedException;
import com.sparta.lv3backoffice.global.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.annotation.Before;



public class AuthorizationAspect {

    private final JwtUtil jwtUtil;

    public AuthorizationAspect(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Before("execution(* com.sparta.lv3backoffice.domain.tutor.service.*.*(..)) "
            + "|| execution(* com.sparta.lv3backoffice.domain.lecture.service.*.*(..)) "
            + "&& args(tokenValue,..)")
    public void checkAuthorityBefore(String tokenValue) {
        // JWT 토큰 substring
        String token = jwtUtil.substringToken(tokenValue);

        // 토큰 검증
        if (!jwtUtil.validateToken(token)) {
            throw new UnauthorizedException("Token Error");
        }

        // 권한이 Manager인지 확인
        Claims info = jwtUtil.getUserInfoFromToken(token);
        String authority = (String) info.get(JwtUtil.AUTHORIZATION_KEY);
        if (!(UserRoleEnum.valueOf(authority)).equals(UserRoleEnum.MANAGER)) {
            throw new UnauthorizedException("사용자는 Manager 권한이 필요합니다.");
        }
    }
}
