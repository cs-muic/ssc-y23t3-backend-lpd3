package io.muzoo.ssc.Project.data;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

public class Token {
    @Getter
    private final String token;
    private final Date issuedAt;
    private final Date expiration;

    public Token(String token, Date issuedAt, Date expiration) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiration = expiration;
    }
    public static Token of(Long userId, Long validityInminutes, String secretKey){
        var issueDate = Instant.now();
        Date issuedAt = Date.from(issueDate);
        Date expiration = Date.from(issueDate.plus(validityInminutes, ChronoUnit.MINUTES));

        String jwtToken = Jwts.builder()
                .claim("user_id", userId)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
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

    public Date getIssuedAt() {
        return this.issuedAt;
    }
    public Date getExpiration() {
        return this.expiration;
    }
}
