package com.navtuan12.job_seeker_server.services;

import org.bson.types.ObjectId;
import com.navtuan12.job_seeker_server.dto.request.UserLoginRequest;
import com.navtuan12.job_seeker_server.dto.request.UserRegisterRequest;
import com.navtuan12.job_seeker_server.dto.request.UserUpdateRequest;
import com.navtuan12.job_seeker_server.dto.response.UserResponse;
import com.navtuan12.job_seeker_server.models.User;

public interface UserService {
    UserResponse register(UserRegisterRequest request);

    UserResponse login(UserLoginRequest request);
    UserResponse update(UserUpdateRequest request, ObjectId id);
    UserResponse findById(String id);
}
