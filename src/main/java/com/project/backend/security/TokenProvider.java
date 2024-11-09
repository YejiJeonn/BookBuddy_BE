package com.project.backend.security;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Collections;
import java.util.Date;

@Component
public class TokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    protected SecretKey key;

    @PostConstruct
    protected void init() {
        key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
    }

    public Authentication getAuthentication(Long id) {
        return CustomAuthenticationToken.builder()
                .principal(id)
                .authorities(Collections.emptyList())
                .credentials(null)
                .build();
    }

    public Long getIdFormAuthentication() {
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String createToken(Long id) {
        return Jwts.builder()
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .subject(id.toString())
                .signWith(key)
                .compact();
    }

    public Long getSubject(String token) {
        try {
            String id = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
            return Long.parseLong(id);
        } catch (Exception e) {
            return null;
        }
    }
}
