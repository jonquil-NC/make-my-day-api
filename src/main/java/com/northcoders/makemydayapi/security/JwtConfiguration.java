package com.northcoders.makemydayapi.security;

import com.northcoders.makemydayapi.dto.LoginRequestDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static javax.crypto.Cipher.SECRET_KEY;

public class JwtConfiguration {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(Authentication authentication) {
        LoginRequestDTO loginRequestDTO = (LoginRequestDTO) authentication.getPrincipal();
        String token = Jwts.builder()
                .setSubject(loginRequestDTO.getEmail())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(5l, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return token;
    }

    public boolean validateToken (String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
