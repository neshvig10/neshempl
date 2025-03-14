package com.neshempl.usermanagement.config;


import com.neshempl.usermanagement.dto.LoginRequest;
import com.neshempl.usermanagement.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.persistence.Id;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${JWT_SECRET}")
    private String SECRET;
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public String generateToken(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("userName", user.getUserName());

        return Jwts.builder()
                .setClaims(claims) // Set custom claims
                .setSubject(String.valueOf(user.getUserId())) // Set userId as subject
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set issue time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiry time
                .signWith(SignatureAlgorithm.HS256, SECRET) // Sign the JWT with the secret key
                .compact();
    }

    public Boolean validateToken(String token,Long userId){
        final Long userID = extractUserId(token);
        return (userID.equals(userId) && !isTokenExpired(token));
    }

    // Method to extract user ID from the token
    public Long extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        System.out.println(claims.toString());
        String userId = claims.getId(); // Ensure 'id' is set in the token
        return userId != null ? Long.valueOf(userId) : null;
    }

    // Method to extract all claims from the token
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)  // Set signing key
                .build()                 // Build parser
                .parseClaimsJws(token)   // Parse JWT
                .getBody();
    }

    public Boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }


}
