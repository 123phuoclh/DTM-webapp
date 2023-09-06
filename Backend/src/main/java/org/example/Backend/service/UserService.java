package org.example.Backend.service;

import org.example.Backend.dto.UserDTO;
import org.example.Backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDTO getUserByID(Long id);

    List<UserDTO> searchName(String keyword);

    User updateUserByID(List<User> list);
}
