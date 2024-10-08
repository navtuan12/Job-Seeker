package com.navtuan12.job_seeker_server.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.navtuan12.job_seeker_server.dto.request.user.UserLoginRequest;
import com.navtuan12.job_seeker_server.dto.request.user.UserRegisterRequest;
import com.navtuan12.job_seeker_server.dto.request.user.UserUpdateRequest;
import com.navtuan12.job_seeker_server.dto.response.ApiResponse;
import com.navtuan12.job_seeker_server.dto.response.UserResponse;
import com.navtuan12.job_seeker_server.services.UserService;
import com.navtuan12.job_seeker_server.utils.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsersController {

    UserService userService;
    JwtService jwtService;

    @PostMapping("/login")
    public ApiResponse<UserResponse> login(@RequestBody UserLoginRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setResult(userService.login(request));
        response.addAdditionalProperty("token", jwtService.generatorToken(request.getEmail()));
        return response;
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserRegisterRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setResult(userService.register(request));
        response.setMessage("Account created successfully");
        response.addAdditionalProperty("token", jwtService.generatorToken(request.getEmail()));
        return response;
    }

    @GetMapping("/get-user")
    public ApiResponse<UserResponse> getUser(HttpServletRequest request) {
        String token = jwtService.getTokenFromRequest(request);
        String email = jwtService.getPayloadFromToken(token);
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setResult(userService.findByEmail(email));
        return response;
    }

    @PutMapping("/update")
    public ApiResponse<UserResponse> updateUser(@RequestBody UserUpdateRequest request,
            HttpServletRequest httpRequest) {
        String token = jwtService.getTokenFromRequest(httpRequest);
        String email = jwtService.getPayloadFromToken(token);
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setResult(userService.update(request, email));
        return response;
    }
}

