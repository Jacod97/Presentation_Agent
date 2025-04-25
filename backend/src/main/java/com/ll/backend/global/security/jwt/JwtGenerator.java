package com.ll.backend.global.security.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtGenerator {
    public String generateAccessToken(final Key ACCESS_SECRET, final long ACCESS_EXPIRATION, User user) {
        Long now = System.currentTimeMillis();
        return Jwts.builder()
                .claims(createClaims(user))
                .compact();
    }

    private Map<String, ?> createClaims(User user) {
        Map<String, Object> claims = new HashMap<>();

        return claims;
    }
}
