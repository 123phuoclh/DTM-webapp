package org.example.Backend.controller;

import org.example.Backend.dto.UserDTO;
import org.example.Backend.model.User;
import org.example.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> searchUser(@RequestParam String keyword, int page) {
        Page<User> result = userService.searchName(keyword, page);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateUserByID(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        if (userService.getUserByID(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Map<String, String> response = new HashMap<>();
        if (userDTO.getEmail() == null) {
            response.put("message", "Email không được để trống");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            userService.updateUserByID(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
