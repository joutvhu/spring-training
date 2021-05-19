package com.joutvhu.training.rest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] PUBLIC_END_POINTS = new String[]{
            "/",
            "/h2/**",
            "/h2-console/**",
            "/api-docs/**",
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable();
        http
                .cors()
                .disable();

        http
                .formLogin()
                .disable();

        http
                .authorizeRequests()
                .antMatchers(PUBLIC_END_POINTS).permitAll()
                .anyRequest().permitAll();
    }
}
