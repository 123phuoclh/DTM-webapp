package org.example.Backend.repository;

import org.example.Backend.model.FriendLists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FriendRepo extends JpaRepository<FriendLists, Long> {

    @Query(value = "select * from friend_lists where user_id = ?1", nativeQuery = true)
    List<FriendLists> getAllFriend(Long userId);

    @Query(value = "select * from friend_lists where (name like :keyword or nick_name like :keyword) and user_id = :userid", nativeQuery = true)
    List<FriendLists> findFriendByName(@Param("keyword") String keyword, @Param("userid") Long id);

    @Modifying
    @Query(value = "insert into friend_lists(address, email, name, nick_name, phone_number, user_id) VALUE (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    void addFriend(String address, String email, String name, String nick_name, String phone_number, Long user_id);

    @Modifying
    @Query(value = "update friend_lists as f set f.nick_name = ?1, f.address =?2, f.phone_number = ?3 where f.id = ?4", nativeQuery = true)
    void editFriend(String nickName, String address, String phoneNumber, Long id);
}
