package org.example.Backend.controller;

import org.example.Backend.dto.FriendDTO;
import org.example.Backend.model.FriendLists;
import org.example.Backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/friend")
@CrossOrigin(value ="http://localhost:4200")
public class FriendController {
    @Autowired
    FriendService friendService;

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAllFriend(@RequestParam String name, @RequestParam Long userID, int pageNo) {
        Page<FriendLists> page = friendService.getAll(name, userID, pageNo);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }


    @PutMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addFriend(@RequestBody FriendDTO obj) {
        if (obj.getUser_id() == null) {
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (obj.getEmail() == null) {
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            this.friendService.addFriend(obj);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> editFriend(@RequestBody FriendDTO obj) {
        if (obj.getId() == null) {
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (obj.getEmail() == null) {
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            this.friendService.editFriend(obj);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteFriend(@PathVariable(value = "id") Long id) {
        this.friendService.deleteFriend(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

