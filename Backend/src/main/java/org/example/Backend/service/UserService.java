package org.example.Backend.service;

import org.example.Backend.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDTO getUserByID(Long id);

    List<UserDTO> searchName(String keyword);
}
