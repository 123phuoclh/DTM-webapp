package org.example.Backend.service.imp;

import org.example.Backend.repository.RoleRepo;
import org.example.Backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepo roleRepo;

    @Override
    public void setRole(Long account_id, int role_id) {
        this.roleRepo.setDefaultRole(account_id, role_id);
    }
}
