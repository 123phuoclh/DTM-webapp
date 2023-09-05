package org.example.Backend.controller;

import org.example.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public ResponseEntity <?> getUserByID(@PathVariable Long Id) {
        return null;
    }
}
