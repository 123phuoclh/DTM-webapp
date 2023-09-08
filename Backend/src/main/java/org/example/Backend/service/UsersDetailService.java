package org.example.Backend.service;

import org.example.Backend.dto.UsersDetailDTO;
import org.example.Backend.model.UsersDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersDetailService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    String existUserEmail(String email);

    void addNew(String name, String email, String username, String hashed_password);
}
