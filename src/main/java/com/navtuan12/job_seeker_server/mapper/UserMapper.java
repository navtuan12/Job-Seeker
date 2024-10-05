package com.navtuan12.job_seeker_server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.navtuan12.job_seeker_server.dto.request.UserRegisterRequest;
import com.navtuan12.job_seeker_server.dto.request.UserUpdateRequest;
import com.navtuan12.job_seeker_server.dto.response.UserResponse;
import com.navtuan12.job_seeker_server.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRegisterRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
