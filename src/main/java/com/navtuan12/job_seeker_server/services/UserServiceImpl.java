package com.navtuan12.job_seeker_server.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.navtuan12.job_seeker_server.dto.request.UserRegisterRequest;
import com.navtuan12.job_seeker_server.dto.request.UserUpdateRequest;
import com.navtuan12.job_seeker_server.models.User;
import com.navtuan12.job_seeker_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(UserRegisterRequest request) {
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

    @Override
    public User update(UserUpdateRequest request, ObjectId id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setContact(request.getContact());
        user.setLocation(request.getLocation());
        user.setJobTitle(request.getJobTitle());
        user.setAbout(request.getAbout());

        return userRepository.save(user);
    }

}
