package org.example.Backend.service;

import jdk.nashorn.internal.runtime.options.Option;
import org.example.Backend.dto.FriendDTO;
import org.example.Backend.exception.ResourceNotFoundException;
import org.example.Backend.model.Friend;
import org.example.Backend.repository.FriendRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {

    private final Logger log = LoggerFactory.getLogger(FriendServiceImpl.class);

    private final FriendRepository friendRepository;


    public FriendServiceImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }


    @Override
    public void deleteById(Long id) {
        friendRepository.deleteById(String.valueOf(id));
    }

    @Override
    public FriendDTO getListFriend() {
        FriendDTO friendDTO = new FriendDTO();
        Pageable pageable = (Pageable) PageRequest.of(0,5, Sort.by("name"));
        List<Friend> page = null;
        return null;
    }

    @Override
    public Friend getById(Long id) {
        Optional<Friend> searchName = friendRepository.findById(String.valueOf(id));
        if (searchName.isPresent()) {
            return searchName.get();
        } else throw new ResourceNotFoundException(id + " not found ");
    }


    @Override
    public int getTotalPage() {
        return friendRepository.findAll().size();
    }
}





