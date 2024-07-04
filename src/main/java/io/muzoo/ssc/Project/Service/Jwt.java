package io.muzoo.ssc.Project.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class Jwt {
    @Getter
    private final String token;
    @Getter
    private final Long userId;
    @Getter
    private final LocalDateTime issuedAt;
    @Getter
    private final LocalDateTime expiration;

    public Jwt(String token, Long userId, LocalDateTime issuedAt, LocalDateTime expiration) {
        this.token = token;
        this.userId = userId;
        this.issuedAt = issuedAt;
        this.expiration = expiration;
    }

    public static Jwt from(String token, String secret) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        Long userId = Long.parseLong(claims.getSubject());
        Instant issuedAtInstant = claims.getIssuedAt().toInstant();
        Instant expirationInstant = claims.getExpiration().toInstant();

        LocalDateTime issuedAt = LocalDateTime.ofInstant(issuedAtInstant, ZoneId.systemDefault());
        LocalDateTime expiration = LocalDateTime.ofInstant(expirationInstant, ZoneId.systemDefault());

        return new Jwt(token, userId, issuedAt, expiration);
    }

    public static String createToken(Long userId, String secret, long expirationTimeInMillis) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

}
