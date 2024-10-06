package com.navtuan12.job_seeker_server.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
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
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService{

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Value("${jwt.SIGNER_KEY}")
    @NonFinal 
    String SECRET_KEY;

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
    public UserResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        var check = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!check) {
            throw new AppException(ErrorCode.WRONG_PASSWORD);
        }
        return userMapper.toUserResponse(user);
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
    public String generatorToken(String email) {
        //define algorithm
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        //define payload 
        JWTClaimsSet claimSet = new JWTClaimsSet.Builder()
            .subject(email)
            .issuer("navtuan12")
            .issueTime(new Date())
            .expirationTime(new Date(
                Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()
            ))
            .build();
        
        Payload payload = new Payload(claimSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("cannot create", e);
            throw new RuntimeException(e);
        }
    }

}
