package org.example.Backend.repository;

import org.example.Backend.model.Friend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FriendRepository extends JpaRepository<Friend, String> {

    @Query(value = "SELECT * from Friend where name like %:inPutName% or nickName like %:inPutName% ", nativeQuery = true)
    Page<Friend> searchFriendByName(@Param("inPutName") String keyWord, Pageable pageable);
    @Query(value = "SELECT COUNT(id) from Friend where name like %:inPutName% or nickName like %:inPutName% ", nativeQuery = true)
    int countFriendByName(@Param("inPutName") String keyWord, Pageable pageable);
}
