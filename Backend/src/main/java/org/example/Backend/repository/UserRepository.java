package org.example.Backend.repository;

import org.example.Backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

    @Query(value = "select * from User where name like %:keyword% or nickName like %:keyword%", nativeQuery = true)
    Page<User> searchUserByKeyword(@Param("keyword") String keyWord, Pageable pageable);
    @Query(value = "select COUNT(id) from User where name like %:keyword% or nickName like %:keyword%", nativeQuery = true)
    int countUserByKeyword(@Param("keyword") String keyWord);
}
