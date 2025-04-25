package com.ll.backend.global.security.jwt;

import com.ll.backend.global.security.dto.CustomUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${custom.jwt.accessSecret}")
    private String accessKey;

    @Value("${custom.jwt.refreshSecret}")
    private String refreshKey;

    @Value("${custom.jwt.accessExpiration}")
    private Long accessExpiration;

    @Value("${custom.jwt.refreshExpiration}")
    private Long refreshExpiration;

    public JwtUtil() {

    }

    public String generateAccessToken(CustomUserDetails user) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .claims(createClaims(user))
                .issuedAt(new Date(now))
                .expiration(new Date(now + accessExpiration))
                .signWith(Keys.hmacShaKeyFor(accessKey.getBytes()))
                .compact();
    }

    private Map<String, ?> createClaims(CustomUserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("memberId", user.getMemberId());
        claims.put("nickname", user.getNickname());
        claims.put("role", user.getRole());

        return claims;
    }
}
