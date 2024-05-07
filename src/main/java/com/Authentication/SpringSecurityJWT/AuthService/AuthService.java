package com.Authentication.SpringSecurityJWT.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Authentication.SpringSecurityJWT.Auth.AuthenticationResponse;
import com.Authentication.SpringSecurityJWT.Entity.User;
import com.Authentication.SpringSecurityJWT.Model.RegisterRequest;
import com.Authentication.SpringSecurityJWT.Repository.UserRepository;

import lombok.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private final UserRepository userRepository;

    public AuthenticationResponse register(RegisterRequest registerRequest){

        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .role(registerRequest.getRole())
                .build();
        var savedUser = userRepository.save(user);

        return null;
    }

}
