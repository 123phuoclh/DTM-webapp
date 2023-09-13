package org.example.Backend.repository;

import org.example.Backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository <User, Long>{

    @Query(value = "select * from users where name like %:keyword% or nick_name like %:keyword%", nativeQuery = true)
    Page<User> searchUserByKeyword(@Param("keyword") String keyWord, Pageable pageable);
    @Query(value = "select COUNT(id) from users where name like %:keyword% or nick_name like %:keyword%", nativeQuery = true)
    int countUserByKeyword(@Param("keyword") String keyWord);

    @Modifying
    @Query(value = "update users as u\n" +
            "set u.name = ?1, u.nick_name =?2, u.email = ?3, u.address = ?4, u.phone_number = ?5, u.avatar= ?6\n" +
            "where u.id = ?7 ", nativeQuery = true)
    void updateUserByID(String name, String nick_name, String email, String address, String phone_number, String avatar, Long id);
}
