package org.example.Backend.repository;

import org.example.Backend.model.UsersDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDetailRepository extends JpaRepository<UsersDetail, String> {
    UsersDetail findUsersDetailByUsername(String username);

    @Query(value = "select email from users_detail where email = ?1",nativeQuery = true)
    String existUserEmail(String email);

    @Modifying
    @Query(value = "insert into users_detail(name, email, username, hashed_password, role_id) values (?1,?2,?3,?4,?5)",nativeQuery = true)
    void addNew(String name, String email, String username, String hashed_password, int role_id);

    @Modifying
    @Query(value = "insert into users(avatar,email,username, name, nick_name, address, phone_number) values ('',?1,?2,?3,'','','')", nativeQuery = true)
    void addNewUser(String email,String username, String name);
}
