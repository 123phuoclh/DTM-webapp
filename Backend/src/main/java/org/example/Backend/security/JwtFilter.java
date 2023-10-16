package org.example.Backend.security;

import org.example.Backend.service.UsersDetailService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;

    private final UsersDetailService usersDetailService;
    public JwtFilter(JwtUtility jwtUtility, UsersDetailService usersDetailService ) {
        this.jwtUtility = jwtUtility;
        this.usersDetailService= usersDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Lấy ra JWT từ request//
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtility.validateJwtToken(jwt)) {
                Authentication authentication = jwtUtility.getUserNameFromJWToken(jwt);
                UserDetails usersDetail = usersDetailService.loadUserByUsername(authentication.getName());
                if (usersDetail.getAuthorities().toString().equals(authentication.getAuthorities().toString())) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }
        //Kiểm tra xem  header Authorization có JWT info không//
    public String parseJwt(HttpServletRequest request) {
             String headerAuth = request.getHeader("Authorization");
            if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
                return headerAuth.substring(7);
            }
            return null;
        }
    }
