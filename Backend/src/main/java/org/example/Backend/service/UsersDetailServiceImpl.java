package org.example.Backend.service;

import org.example.Backend.repository.UsersDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsersDetailServiceImpl implements UsersDetailService {
    @Autowired
    private UsersDetailRepository usersDetailRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username){
        UserDetails usersDetail = usersDetailRepository.findUsersDetailByUserName(username);
        if (usersDetail.getUsername() == null) {
            throw new UsernameNotFoundException("User not found with the username of: " + username);
        } else {
            return usersDetail;
        }
    }

    @Override
    public String existUserEmail(String email) {
        return usersDetailRepository.existUserEmail(email);
    }
}

