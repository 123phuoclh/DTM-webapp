package org.example.Backend.repository;

import org.example.Backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    @Modifying
    @Query(value = " insert into account_role(account_id, role_id) value(?1,?2)", nativeQuery = true)
    void setDefaultRole(Long account_id, int role_id);
}
