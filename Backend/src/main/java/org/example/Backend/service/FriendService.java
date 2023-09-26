package org.example.Backend.service;

import org.example.Backend.dto.FriendDTO;
import org.example.Backend.dto.UserDTO;
import org.example.Backend.model.FriendLists;
import org.example.Backend.model.User;
import org.springframework.data.domain.Page;


public interface FriendService {
    Page<FriendLists> getAll(String name, Long userId, int page);

//    Page<UserDTO> searchToAddFriend(String name, Long id, int pageNo);

    void addFriend(FriendDTO friendDTO);

    void deleteFriend(Long id);
}
