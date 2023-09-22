package org.example.Backend.service.imp;

import org.example.Backend.model.UsersDetail;
import org.example.Backend.model.UsersDetailCustom;
import org.example.Backend.repository.UsersDetailRepository;
import org.example.Backend.service.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersDetailServiceImpl implements UsersDetailService {
    @Autowired
    private UsersDetailRepository usersDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UsersDetail usersDetail = usersDetailRepository.findUsersDetailByUsername(username);
        if (usersDetail.getUsername() == null) {
            throw new UsernameNotFoundException("User not found with the username of: " + username);
        }

        return UsersDetailCustom.build(usersDetail);
    }

    @Override
    public String existUserEmail(String email) {
        return usersDetailRepository.existUserEmail(email);
    }

    @Override
    public void addNew(String name, String email, String username, String hashed_password) {
        usersDetailRepository.addNew(name, email, username, hashed_password);
    }

    @Override
    public void addNewUser(String email, String username, String name) {
        usersDetailRepository.addNewUser(email,username,name );
    }

    @Override
    public UsersDetail getUserDetailByUserName(String username) {
        return usersDetailRepository.findUsersDetailByUsername(username);
    }
}

