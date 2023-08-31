package org.example.Backend.service;

import org.example.Backend.dto.FriendDTO;
import org.example.Backend.model.Friend;

import java.util.Optional;

public interface FriendService {

    void deleteById(Long id);

    FriendDTO getListFriend();

    Friend getById(Long id);

    int getTotalPage();

}