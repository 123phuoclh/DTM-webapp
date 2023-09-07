package org.example.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersDetailRepository extends JpaRepository {
    UserDetails findUsersDetailByUserName(String username);

    @Query(value = "select email from UsersDetail where email = ?1",nativeQuery = true)
    String existUserEmail(String email);
}
