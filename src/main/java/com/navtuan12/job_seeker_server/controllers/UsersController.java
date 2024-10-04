package com.navtuan12.job_seeker_server.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.navtuan12.job_seeker_server.dto.request.UserLoginRequest;
import com.navtuan12.job_seeker_server.dto.request.UserRegisterRequest;
import com.navtuan12.job_seeker_server.dto.request.UserUpdateRequest;
import com.navtuan12.job_seeker_server.models.User;
import com.navtuan12.job_seeker_server.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }
    
    @PostMapping("/register")
    public User createUser(@RequestBody @Valid UserRegisterRequest request) {
        return userService.register(request);
    }

    @GetMapping("/get-user/{userId}")
    public User getUser(@PathVariable String userId) {
        User user = userService.findById(userId);
        return user;
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody UserUpdateRequest request, @PathVariable ObjectId id) {
        return userService.update(request, id);
    }
}

