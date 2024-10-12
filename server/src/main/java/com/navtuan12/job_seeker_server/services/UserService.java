package com.navtuan12.job_seeker_server.services;

import org.bson.types.ObjectId;
import com.navtuan12.job_seeker_server.dto.request.user.UserLoginRequest;
import com.navtuan12.job_seeker_server.dto.request.user.UserRegisterRequest;
import com.navtuan12.job_seeker_server.dto.request.user.UserUpdateRequest;
import com.navtuan12.job_seeker_server.dto.response.UserResponse;

public interface UserService {
    UserResponse register(UserRegisterRequest request);
    UserResponse login(UserLoginRequest request);
    UserResponse update(UserUpdateRequest request, String email);
    UserResponse findByEmail(String email);
}
