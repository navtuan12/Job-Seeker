package com.navtuan12.job_seeker_server.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.navtuan12.job_seeker_server.dto.request.UserRegisterRequest;
import com.navtuan12.job_seeker_server.dto.request.UserUpdateRequest;
import com.navtuan12.job_seeker_server.models.User;
import com.navtuan12.job_seeker_server.services.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody UserRegisterRequest request) {
        return userService.register(request);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody UserUpdateRequest request, @PathVariable ObjectId id) {
        return userService.update(request, id);
    }
    
}

