package com.navtuan12.job_seeker_server.controllers;

import org.bson.types.ObjectId;
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
import com.navtuan12.job_seeker_server.dto.response.ApiResponse;
import com.navtuan12.job_seeker_server.dto.response.UserResponse;
import com.navtuan12.job_seeker_server.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsersController {

    UserService userService;
    
    @PostMapping("/login")
    public ApiResponse<UserResponse> login(@RequestBody UserLoginRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setResult(userService.login(request));
        response.addAdditionalProperty("token", userService.generatorToken(request.getEmail()));
        return response;
    }
    
    @PostMapping("/register")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserRegisterRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setResult(userService.register(request)); 
        return response;
    }

    @GetMapping("/get-user/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable String userId) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setResult(userService.findById(userId));
        return response;
    }

    @PutMapping("/update/{id}")
    public ApiResponse<UserResponse> updateUser(@RequestBody UserUpdateRequest request, @PathVariable ObjectId id) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setResult(userService.update(request, id));
        return response;
    }
}

