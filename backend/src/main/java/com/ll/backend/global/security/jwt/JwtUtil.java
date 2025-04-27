package com.ll.backend.global.security.jwt;

import com.ll.backend.domain.member.member.entity.Member;
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

    public String generateAccessToken(Member member, int type) {
        long now = System.currentTimeMillis();

        if (type == 1) {
            return Jwts.builder()
                    .claims(createClaims(member))
                    .issuedAt(new Date(now))
                    .expiration(new Date(now + accessExpiration))
                    .signWith(Keys.hmacShaKeyFor(accessKey.getBytes()))
                    .compact();
        }
        else {
            return Jwts.builder()
                    .claims(createClaims(member))
                    .issuedAt(new Date(now))
                    .expiration(new Date(now + refreshExpiration))
                    .signWith(Keys.hmacShaKeyFor(refreshKey.getBytes()))
                    .compact();
        }

    }

    private Map<String, ?> createClaims(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", member.getUsername());
        claims.put("memberId", member.getId());
        claims.put("nickname", member.getNickname());
        claims.put("role", member.getRole());

        return claims;
    }
}
