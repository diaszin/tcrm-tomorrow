package br.tomorrow.tcrm.configuration;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtUtil {
    @Value("${api.security.jwt.secret}")
    private String secret;

    @Value("${api.security.jwt.expiration}")
    private int expiration;

    public SecretKey getKey() {
        return Keys.hmacShaKeyFor(Keys.hmacShaKeyFor(secret.getBytes()).getEncoded());
    }

    public String generateToken(UUID id) {

        Map<String, String> claims = new HashMap<>();
        claims.put("id", id.toString());
        long expirationTime = System.currentTimeMillis() + (expiration * (24 * 60 * 60 * 1000));
        Date expirationDate = new Date(expirationTime);

        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expirationDate)
                .signWith(this.getKey())
                .compact();
    }

    public UUID getIdFromToken(String token) {
        Claims body = Jwts.parser()
                .verifyWith(this.getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return UUID.fromString(body.get("id").toString());
    }

    public boolean validateToken(String token) {
        try {
            JwtParser parser = Jwts.parser()
                    .verifyWith(this.getKey())
                    .build();
            parser.parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            System.out.println("passou aqui");
            return false;
        }
    }
}
