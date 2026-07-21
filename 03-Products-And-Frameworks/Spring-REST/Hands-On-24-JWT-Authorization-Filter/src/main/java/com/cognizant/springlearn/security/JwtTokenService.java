package com.cognizant.springlearn.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenService.class);

    private final SecretKey secretKey;
    private final long expirationMinutes;

    public JwtTokenService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration-minutes}") long expirationMinutes) {
        this.secretKey = buildSigningKey(secret);
        this.expirationMinutes = expirationMinutes;
    }

    static SecretKey buildSigningKey(String secret) {
        try {
            byte[] keyBytes = MessageDigest.getInstance("SHA-256")
                    .digest(secret.getBytes(StandardCharsets.UTF_8));
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 not available", ex);
        }
    }

    public String generateToken(UserDetails userDetails) {
        LOGGER.info("START JWT generation user={}", userDetails.getUsername());
        Instant now = Instant.now();
        String token = Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(expirationMinutes * 60)))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
        LOGGER.info("END JWT generation");
        return token;
    }

    public String generateTokenWithExpiration(UserDetails userDetails, Instant expiresAt) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiresAt))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public Claims parseClaims(String token) {
        LOGGER.info("START JWT validation");
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            LOGGER.info("END JWT validation subject={}", claims.getSubject());
            return claims;
        } catch (JwtException ex) {
            LOGGER.warn("JWT validation failed: {}", ex.getMessage());
            throw ex;
        }
    }
}
