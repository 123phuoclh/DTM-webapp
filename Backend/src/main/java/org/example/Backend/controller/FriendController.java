package org.example.Backend.controller;

import org.example.Backend.dto.FriendDTO;
import org.example.Backend.model.FriendLists;
import org.example.Backend.model.User;
import org.example.Backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/friend")
@CrossOrigin(value ="http://localhost:4200")
public class FriendController {
    @Autowired
    FriendService friendService;

    @GetMapping("")
    public ResponseEntity<?> getAllFriend(@RequestParam String keyword, @RequestParam Long id, int pageNo) {
        Page<FriendLists> page = friendService.getAll(keyword, id, pageNo);
            return new ResponseEntity<>(page.get(), HttpStatus.OK);
        }

    @GetMapping("/serch")
    public ResponseEntity<?> searchFriend(@RequestParam String keyword, @RequestParam Long id, int pageNo) {
        Page<User> result = friendService.searchToAddFriend(keyword,id, pageNo);
            return new  ResponseEntity<>(result.get(), HttpStatus.OK);
        }

    @PostMapping("/add")
    public ResponseEntity<?> addFriend(@RequestBody FriendDTO friendDTO) {
        if (friendDTO.getUser_id() == null) {
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (friendDTO.getEmail() == null) {
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            this.friendService.addFriend(friendDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}

