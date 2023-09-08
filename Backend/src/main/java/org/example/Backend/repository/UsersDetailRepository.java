package org.example.Backend.repository;

import org.hibernate.sql.Insert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersDetailRepository extends JpaRepository {
    UserDetails findUsersDetailByUserName(String username);

    @Query(value = "select email from UsersDetail where email = ?1",nativeQuery = true)
    String existUserEmail(String email);

    @Modifying
    @Query(value = "insert into UsersDetail(name, email, username, hashed_password) value (?1,?2,?3,?4)",nativeQuery = true)
    void addNew(String name, String email, String username, String hashed_password);
}
