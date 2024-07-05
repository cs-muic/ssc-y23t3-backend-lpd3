package io.muzoo.ssc.Project.data;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

public class Token {
    @Getter
    private final String token;
    private final LocalDateTime issuedAt;
    private final LocalDateTime expiration;

    public Token(String token, LocalDateTime issuedAt, LocalDateTime expiration) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiration = expiration;
    }
    public static Token of(Long userId, Long validityInminutes, String secretKey){
        var issueDate = Instant.now();
        LocalDateTime issuedAt = LocalDateTime.ofInstant(issueDate, ZoneId.systemDefault());
        LocalDateTime expiration = issuedAt.plus(validityInminutes, ChronoUnit.MINUTES);

        String jwtToken = Jwts.builder()
                .claim("user_id", userId)
                .setIssuedAt(Date.from(issueDate))
                .setExpiration(Date.from(issueDate.plus(validityInminutes, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
        return new Token(jwtToken, issuedAt, expiration);
    }

    public static Token of(String token){
        return new Token(token,null,null);
    }
    public static Long from(String token, String secretKey){
        return ((Claims) Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parse(token)
                .getBody())
                .get("user_id",Long.class);
    }
    public String refreshToken() {
        return this.token;
    }

    public LocalDateTime getIssuedAt() {
        return this.issuedAt;
    }
    public LocalDateTime getExpiration() {
        return this.expiration;
    }
}
