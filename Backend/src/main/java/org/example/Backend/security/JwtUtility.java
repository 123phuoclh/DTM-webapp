package org.example.Backend.security;
import io.jsonwebtoken.*;
import org.example.Backend.dto.UsersDetailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtUtility implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtility.class);
    private final String jwtSecret = "secretkey";

    public String generateJwtToken(UsersDetailDTO usersDetailDTO) {
        return Jwts.builder()
                .setSubject(usersDetailDTO.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + (100L * 24 * 60 * 60 * 1000)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJWToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
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
