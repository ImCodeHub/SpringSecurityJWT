package com.Authentication.SpringSecurityJWT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Authentication.SpringSecurityJWT.Auth.AuthenticationResponse;
import com.Authentication.SpringSecurityJWT.AuthService.AuthService;
import com.Authentication.SpringSecurityJWT.Model.RegisterRequest;

@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        AuthenticationResponse authResponse = authService.register(registerRequest);
        
    }
}
