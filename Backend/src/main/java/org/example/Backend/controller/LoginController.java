package org.example.Backend.controller;

import org.example.Backend.dto.UsersDetailDTO;
import org.example.Backend.model.User;
import org.example.Backend.model.UsersDetail;
import org.example.Backend.model.UsersDetailCustom;
import org.example.Backend.payload.JwtResponse;
import org.example.Backend.security.JwtUtility;
import org.example.Backend.service.UserService;
import org.example.Backend.service.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
    @Autowired
    UserService userService;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> Login(@Valid @RequestBody UsersDetailDTO usersDetailDTO) throws RuntimeException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usersDetailDTO.getUsername(), usersDetailDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(usersDetailDTO);
        UsersDetailCustom userDetail = (UsersDetailCustom) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> role = userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        UsersDetail usersDetail = usersDetailService.getUserDetailByUserName(usersDetailDTO.getUsername());
        User user = userService.getUserByEmail(usersDetail.getEmail());
        return ResponseEntity.ok(new JwtResponse(jwt, user.getId(), usersDetail.getUsername(), role, user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody UsersDetailDTO usersDetailDTO) {
        if (usersDetailService.existUserEmail(usersDetailDTO.getEmail()) != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Email đã tồn tại");
            return ResponseEntity.badRequest().body(response);
        }
        if (usersDetailService.getUserDetailByUserName(usersDetailDTO.getUsername()) != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Tên tài khoản đã tồn tại");
            return ResponseEntity.badRequest().body(response);
        } else {
            UsersDetail usersDetail = new UsersDetail(usersDetailDTO.getName(), usersDetailDTO.getEmail(), usersDetailDTO.getUsername(), passwordEncoder.encode(usersDetailDTO.getPassword()));
            usersDetailService.addNew(usersDetail.getName(), usersDetail.getEmail(), usersDetail.getUsername(), usersDetail.getHashed_password());
            usersDetailService.addNewUser(usersDetail.getEmail(), usersDetail.getName());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Đăng ký tài khoản thành công");
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/logout")
    public void Logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}

