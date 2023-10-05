package org.example.Backend.service;

import org.example.Backend.dto.FriendDTO;
import org.example.Backend.model.FriendLists;
import org.springframework.data.domain.Page;


public interface FriendService {
    Page<FriendLists> getAll(String name, Long userId, int page);

    void addFriend(FriendDTO friendDTO);

    void deleteFriend(Long id);

    void editFriend(FriendDTO friendDTO);
}
