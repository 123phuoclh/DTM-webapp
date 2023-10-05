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


@Repository
@Transactional
public interface UserRepository extends JpaRepository <User, Long>{

    @Modifying
    @Query(value = "UPDATE users AS u " +
            "SET u.name = :name, u.nick_name = :nick_name, u.email = :email, u.address = :address, u.phone_number = :phone_number, u.avatar = :avatar " +
            "WHERE u.id = :id", nativeQuery = true)
    void editUser(@Param("name") String name, @Param("nick_name") String nick_name, @Param("email") String email,
                           @Param("address") String address, @Param("phone_number") String phone_number,
                           @Param("avatar") String avatar, @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE users_detail AS d SET d.email = :email, d.name = :name WHERE d.username = :username", nativeQuery = true)
    void editUserDetail(@Param("name") String name, @Param("email") String email, @Param("username") String username);

    @Query(value = "select * from users where name like :keyword and email not in (select email from friend_lists where user_id = :id) and id <> :id", nativeQuery = true)
    Page<User> searchUser(@Param("keyword") String keyword,@Param("id") Long id, Pageable pageable);


    @Query(value = "select * from users where email = ?1",nativeQuery = true)
    User getUserByEmail(String email);
}
