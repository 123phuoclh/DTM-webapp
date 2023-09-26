package org.example.Backend.service;

import org.example.Backend.dto.UserDTO;
import org.example.Backend.model.User;
import org.springframework.data.domain.Page;

public interface UserService {
    UserDTO getUserByID(Long id);

    Page<UserDTO> searchName(String keyword,Long userid, int page);
    void updateUserByID(UserDTO userDTO);
    User getUserByEmail(String email);
}
