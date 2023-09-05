package org.example.Backend.service;

import org.example.Backend.dto.UserDTO;
import org.example.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    UserDTO userDTO;

    @Override
    public UserDTO getUserByID(Long id) {

        return userDTO;
    }
}
