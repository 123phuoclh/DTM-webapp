package org.example.Backend.security;
import io.jsonwebtoken.*;
import org.example.Backend.model.UsersDetailCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtUtility implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtility.class);
    private final String jwtSecret = "secretkey";
    private final String AUTHORITIES_KEY  = "authorities";

    public String generateJwtToken(UsersDetailCustom usersDetailCustom) {
        List<String> authorities = usersDetailCustom.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
                .setSubject(usersDetailCustom.getUsername())
                .claim(AUTHORITIES_KEY, authorities.get(0))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + (100L * 24 * 60 * 60 * 1000)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Authentication getUserNameFromJWToken(String token) {
        Claims claims =  Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Stream.of(claims.get(AUTHORITIES_KEY).toString()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(claims.getSubject(),token, authorities);
    }



    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
