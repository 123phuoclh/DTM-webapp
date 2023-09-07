package org.example.Backend.controller;

import org.example.Backend.dto.UsersDetailDTO;
import org.example.Backend.security.JwtUtility;
import org.example.Backend.service.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsersDetailService usersDetailService;
    @Autowired
    private JwtUtility jwtUtility;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody UsersDetailDTO usersDetailDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usersDetailDTO.getUsername(), usersDetailDTO.getHashed_password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(usersDetailDTO);
        return ResponseEntity.ok(jwt);
    }
    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody UsersDetailDTO usersDetailDTO) {
        if (!usersDetailService.existUserEmail(usersDetailDTO.getEmail()).isEmpty()) {
            return ResponseEntity.badRequest().body(usersDetailDTO);
        }
    }
}
