package com.java.SchoolManagementSystem.configuration;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.java.SchoolManagementSystem.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    @Autowired
    public JWTAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    //filter JWT
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authedHeader = request.getHeader("Authorization");
        final  String JWT;
        System.out.println(authedHeader);
        if(Objects.isNull(authedHeader) || !authedHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        // get JWT
        JWT = authedHeader.substring(7); 
        //check validation token
        DecodedJWT decodedJWT = jwtService.isTokenCorrect(JWT);
        //verify token
        if(Objects.isNull(decodedJWT)){
            filterChain.doFilter(request,response);
            return;
        }

        // Check if user not authentication yet
        if(SecurityContextHolder.getContext().getAuthentication() == null){

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(decodedJWT.getClaim("role").asString()));

            // create authentication token for the user
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    decodedJWT.getSubject(),
                    null,
                    authorities);
            // set the authentication token in the SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);

    }
}
