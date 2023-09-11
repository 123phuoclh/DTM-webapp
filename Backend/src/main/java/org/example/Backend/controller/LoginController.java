package org.example.Backend.controller;

import org.example.Backend.dto.UsersDetailDTO;
import org.example.Backend.model.UsersDetail;
import org.example.Backend.security.JwtUtility;
import org.example.Backend.service.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsersDetailService usersDetailService;
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@Valid @RequestBody UsersDetailDTO usersDetailDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usersDetailDTO.getUsername(), usersDetailDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(usersDetailDTO);
        UserDetails usersDetail = usersDetailService.loadUserByUsername(usersDetailDTO.getUsername());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody UsersDetailDTO usersDetailDTO) {
        if (usersDetailService.existUserEmail(usersDetailDTO.getEmail()) != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message","Email đã tồn tại");
            return ResponseEntity.badRequest().body(response);
        } else {
            UsersDetail usersDetail = new UsersDetail(usersDetailDTO.getName(),usersDetailDTO.getEmail(), usersDetailDTO.getUsername(), passwordEncoder.encode(usersDetailDTO.getPassword()));
            usersDetailService.addNew(usersDetail.getName(), usersDetail.getEmail(), usersDetail.getUsername(), usersDetail.getHashed_password());
            Map<String, String> response = new HashMap<>();
            response.put("message","Đăng ký tài khoản thành công");
            return ResponseEntity.ok(response);
        }
    }
    @PostMapping("/logout")
    public void Logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}

