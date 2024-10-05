package com.navtuan12.job_seeker_server.services;

import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.navtuan12.job_seeker_server.dto.request.UserLoginRequest;
import com.navtuan12.job_seeker_server.dto.request.UserRegisterRequest;
import com.navtuan12.job_seeker_server.dto.request.UserUpdateRequest;
import com.navtuan12.job_seeker_server.dto.response.UserResponse;
import com.navtuan12.job_seeker_server.exception.AppException;
import com.navtuan12.job_seeker_server.exception.ErrorCode;
import com.navtuan12.job_seeker_server.mapper.UserMapper;
import com.navtuan12.job_seeker_server.models.User;
import com.navtuan12.job_seeker_server.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService{

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(UserRegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse update(UserUpdateRequest request, ObjectId id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user, request);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse findById(String id) {
        return userMapper.toUserResponse(userRepository.findById(new ObjectId(id))
                    .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public UserResponse login(UserLoginRequest request) {
        User dbUser = userRepository.findByEmail(request.getEmail());

        if (dbUser == null) {
            throw new RuntimeException("Wrong email or password!");
        }

        if (!dbUser.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Wrong email or password!");
        }

        return userMapper.toUserResponse(dbUser);

    }

}
