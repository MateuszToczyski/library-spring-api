package com.library.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenGenerator {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private String secretKey;

    @Value("${jwt.token.validity}")
    private long tokenValidity;

    public JwtTokenResponse generateToken(JwtAuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));

        long currentTime = System.currentTimeMillis();

        String token = Jwts.builder()
                .setSubject(request.getUsername())
                .claim("roles", authentication.getAuthorities())
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + tokenValidity))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();

        return new JwtTokenResponse(token);
    }
}
