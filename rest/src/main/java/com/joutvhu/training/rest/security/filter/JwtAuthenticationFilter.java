package com.joutvhu.training.rest.security.filter;

import com.joutvhu.training.rest.security.jwt.JwtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtService jwtService;

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("doFilterInternal request : {}", request);

        Authentication authentication = jwtService.getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.debug("WebSecurityConfigurerAdapter before next filter");
        filterChain.doFilter(request, response);
        log.debug("WebSecurityConfigurerAdapter after next filter");
    }
}
