package com.java.SchoolManagementSystem.configuration;

import com.java.SchoolManagementSystem.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    private final JwtService jwtService;

    @Autowired
    public JWTFilterConfig(JwtService jwtService ) {
        this.jwtService = jwtService;
    }

    @Override
    public void configure(HttpSecurity http) {
        JWTAuthenticationFilter jwtFilter = new JWTAuthenticationFilter(jwtService);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
