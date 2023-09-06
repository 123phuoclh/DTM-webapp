package org.example.Backend.service;

import org.example.Backend.dto.UserDTO;
import org.example.Backend.dto.UserMapper;
import org.example.Backend.exception.ResourceNotFoundException;
import org.example.Backend.model.User;
import org.example.Backend.repository.UserRepository;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    private static final ModelMapper modelMapper = new ModelMapper();
    public double getTotalPage() {
        return userRepository.findAll().size();
    }
    public List<UserDTO> convertEntityToDTOList(List<User> userList){
        Type listType = new TypeToken<List<UserDTO>>(){}.getType();
        return modelMapper.map(userList, listType);
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
    public List<UserDTO> searchName(String keyword) {
        List<UserDTO> userListDTO;
        Pageable paging = PageRequest.of(0, 5);
        List<User> pageResult = null;
        int pages;
        if (keyword.isEmpty()) {
            pageResult = userRepository.findAll(paging).toList();
            pages = (int) Math.ceil(getTotalPage() / 5);
        } else {
            pageResult = userRepository.searchUserByKeyword(keyword, paging).toList();
            pages = (int) Math.ceil((double) userRepository.countUserByKeyword(keyword) / 5);
        }
        userListDTO = convertEntityToDTOList(pageResult);
        return userListDTO;
    }

    @Override
    public User updateUserByID(List<User> list) {
        return null;
    }
}
