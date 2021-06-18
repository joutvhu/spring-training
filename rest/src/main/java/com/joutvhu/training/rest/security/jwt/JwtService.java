package com.joutvhu.training.rest.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    public static final long EXPIRATION_TIME = 3_600_000; // 1 hour
    public static final String SECRET = "secret-key";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";

    public String buildJwtToken(String username) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return jwt;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);

        if (StringUtils.isNotBlank(token) && token.startsWith(TOKEN_PREFIX)) {
            token = token.replace(TOKEN_PREFIX, "").trim();

            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            List<GrantedAuthority> fakeAuthorities = new ArrayList<>();
            fakeAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, fakeAuthorities) :
                    null;
        }
        return null;
    }
}
