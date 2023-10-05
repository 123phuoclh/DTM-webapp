package org.example.Backend.service.imp;

import org.example.Backend.dto.FriendDTO;
import org.example.Backend.model.FriendLists;
import org.example.Backend.repository.FriendRepo;
import org.example.Backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendRepo friendRepo;


    @Override
    public Page<FriendLists> getAll(String name, Long userId, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        String name1 = '%' + name + '%';
        List<FriendLists> friendLists;
        if (name.equals("")) {
            friendLists = friendRepo.getAllFriend(userId);
        } else {
            friendLists = friendRepo.findFriendByName(name1, userId);
        }
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), friendLists.size());
        List<FriendLists> result = friendLists.subList(start, end);
        return new PageImpl<>(result, pageable, friendLists.size());
    }


    @Override
    public void addFriend(FriendDTO friendDTO) throws RuntimeException {
        this.friendRepo.addFriend(friendDTO.getAddress(), friendDTO.getEmail(), friendDTO.getName(), friendDTO.getNick_name(), friendDTO.getPhoneNumber(),friendDTO.getUser_id());
    }
    @Override
    public void deleteFriend(Long id) {
        this.friendRepo.deleteById(id);
    }

    @Override
    public void editFriend(FriendDTO friendDTO) {
        this.friendRepo.editFriend(friendDTO.getNick_name(), friendDTO.getAddress(), friendDTO.getPhoneNumber(), friendDTO.getId());
    }
}
