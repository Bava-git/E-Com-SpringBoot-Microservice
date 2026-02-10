package com.khapara.userservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
@Data
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.expirationTime}")
    private long expirationTime;

    private final KeyProvider keyProvider;

    public JwtUtil(KeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails) throws Exception {
        String token = Jwts.builder()
                .header()
                .add("kid", "jwtkey")
                .and()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(keyProvider.getPrivateKey(), Jwts.SIG.RS256)
                .compact();
        return token;
    }

    public Claims extractAllClaims(String token) throws Exception {
        return Jwts.parser()
                .verifyWith(keyProvider.loadPublicKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws Exception {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractEmail(String token) throws Exception {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) throws Exception {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) throws Exception {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) throws Exception {
        final String extractedUsername = extractEmail(token);
        return (extractedUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
