package com.example.springfletta.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.springfletta.entity.User;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {

    private static final String SECRET_KEY = "MySuperSecretKeyForJwtTokenGeneration123!";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간
    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // ✅ JWT 토큰 생성
    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return Jwts.builder()
                .subject(user.getUsername()) // ✅ userId 사용
                .claim("userId", user.getId()) // ✅ 추가 정보 저장 가능
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    // ✅ JWT 토큰 검증 및 파싱
    public Claims validateToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // ✅ 토큰에서 userId 추출
    public String getUserIdFromToken(String token) {
        return validateToken(token).get("userId", String.class);
    }
}

