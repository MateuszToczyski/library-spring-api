package com.library.security;


import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final String secretKey;
    private final Gson gson = new Gson();

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, String secretKey) {
        super(authenticationManager);
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        UsernamePasswordAuthenticationToken authenticationToken =
                header == null ? null : getAuthenticationByToken(header);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String header) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(header.replace("Bearer ", ""));

        String username = claimsJws.getBody().get("sub").toString();

        List<AuthorityWrapper> wrappedAuthorities =
                Arrays.asList(gson.fromJson(claimsJws.getBody().get("roles").toString(), AuthorityWrapper[].class));

        Set<SimpleGrantedAuthority> authorities = wrappedAuthorities.stream()
                .map(authorityWrapper -> new SimpleGrantedAuthority(authorityWrapper.getAuthority()))
                .collect(Collectors.toSet());

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
