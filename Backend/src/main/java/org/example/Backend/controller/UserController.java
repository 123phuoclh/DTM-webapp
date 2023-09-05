package org.example.Backend.controller;

import org.example.Backend.dto.UserDTO;
import org.example.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id) {
        UserDTO result = userService.getUserByID(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam String keyword) {
        List<UserDTO> result = userService.searchName(keyword);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserByID(@PathVariable Long id) {
        return null;
    }
}
