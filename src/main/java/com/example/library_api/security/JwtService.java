package com.example.library_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
//    Secret key used for signing JWTs, injected from application properties
    @Value("${jwt.secret}")
    private String secretKey;

//    Token expiration time in ms
    @Value("${jwt.expiration}")
    private Long expirationMs;

//    Generate a JWT for the given user details
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigninKey())
                .compact();
    }

//    Validate if the provided JWT is valid for the given user details
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

//    Check if the provided JWT is expired
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

//    Extract the username (subject) from the provided JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

//    Extract a specific claim from the provided JWT using the given claims resolver
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

//    Generate a signing key from the secret key string
//    Use HMAC-SHA algorithm for key generation
    private SecretKey getSigninKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
