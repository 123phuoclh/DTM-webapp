package org.example.Backend.service.imp;

import org.example.Backend.dto.UserDTO;
import org.example.Backend.dto.UserMapper;
import org.example.Backend.exception.ResourceNotFoundException;
import org.example.Backend.model.User;
import org.example.Backend.repository.UserRepository;
import org.example.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public double getTotalPage() {
        return userRepository.findAll().size();
    }

    @Override
    public UserDTO getUserByID(Long id) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found with the id of: " + id);
        } else {
            return UserMapper.toUserDTO(user.get());
        }
    }

    @Override
    public Page<User> searchName(String keyword, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        Page page = userRepository.searchUserByKeyword(keyword, pageable);
        return page;
    }

    @Override
    public void updateUserByID(UserDTO userDTO) {
        this.userRepository.editUser(userDTO.getName(), userDTO.getNickName(), userDTO.getEmail(), userDTO.getAddress(), userDTO.getPhoneNumber(), userDTO.getAvatar(), userDTO.getId());
        this.userRepository.editUserDetail(userDTO.getName(), userDTO.getEmail(), userDTO.getUsername());
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
