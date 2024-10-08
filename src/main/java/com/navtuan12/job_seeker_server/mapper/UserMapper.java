package com.navtuan12.job_seeker_server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.navtuan12.job_seeker_server.dto.request.user.UserRegisterRequest;
import com.navtuan12.job_seeker_server.dto.request.user.UserUpdateRequest;
import com.navtuan12.job_seeker_server.dto.response.UserResponse;
import com.navtuan12.job_seeker_server.models.User;

@Mapper(componentModel = "spring")
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface UserMapper {

    @Mapping(target = "cpassword", ignore=true)
    User toUser(UserRegisterRequest request);
    UserResponse toUserResponse(User user);

    @Mappings({
        @Mapping(source = "token", target = "token", ignore = true),
        @Mapping(source = "updatedAt", target = "updatedAt", ignore = true),
        @Mapping(source = "id", target = "id", ignore = true)
    })
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
