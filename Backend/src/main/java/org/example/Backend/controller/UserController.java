package org.example.Backend.controller;

import org.example.Backend.dto.UserDTO;
import org.example.Backend.model.User;
import org.example.Backend.service.UserService;
import org.example.Backend.service.UsersDetailService;
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
@RequestMapping("dashboard/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UsersDetailService usersDetailService;

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

    @PutMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateUserByID(@Valid @RequestBody UserDTO userDTO) {
        if (userService.getUserByID(userDTO.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Map<String, String> response = new HashMap<>();
        if (usersDetailService.existUserEmail(userDTO.getEmail()) != null) {
            if (!userService.getUserByEmail(userDTO.getEmail()).getId().equals(userDTO.getId())) {
                response.put("message", "Email đã tồn tại");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {

                userService.updateUserByID(userDTO);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else {
            userService.updateUserByID(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
