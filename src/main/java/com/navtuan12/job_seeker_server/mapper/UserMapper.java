package com.navtuan12.job_seeker_server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.navtuan12.job_seeker_server.dto.request.user.UserRegisterRequest;
import com.navtuan12.job_seeker_server.dto.request.user.UserUpdateRequest;
import com.navtuan12.job_seeker_server.dto.response.UserResponse;
import com.navtuan12.job_seeker_server.models.User;

@Mapper(componentModel = "spring")
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface UserMapper {
    User toUser(UserRegisterRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
