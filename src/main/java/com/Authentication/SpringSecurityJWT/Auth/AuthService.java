package com.Authentication.SpringSecurityJWT.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Authentication.SpringSecurityJWT.Config.JwtService;
import com.Authentication.SpringSecurityJWT.Entity.User;
import com.Authentication.SpringSecurityJWT.Model.AuthenticationRequest;
import com.Authentication.SpringSecurityJWT.Model.RegisterRequest;
import com.Authentication.SpringSecurityJWT.Repository.UserRepository;

import lombok.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {

        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();
        var savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // first step
        // we need to validate out request (validate whether password & username is
        // correct)
        // verify whether user present in the database
        // which AuthenticataionProvider -> DaoAuthenticationProvider(Inject)
        // we need to authenticate using authenicationManager injecting this
        // authenticationProvider
        // second step
        // verify whether username and password is correct =>
        // UserNamePasswordAuthenticationToken
        // verify whether user present in db
        // generateToken
        // return the token

        // authenticationManager.authenticate(new
        // UsernamePasswordAuthenticationToken(request.getEmail(),
        // request.getPassword()));
        // var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        // String jwtToken = jwtService.generateToken(user);
        // return AuthenticationResponse.builder().accessToken(jwtToken).build();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

}
