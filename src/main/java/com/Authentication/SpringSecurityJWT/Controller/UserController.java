package com.Authentication.SpringSecurityJWT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Authentication.SpringSecurityJWT.Auth.AuthService;
import com.Authentication.SpringSecurityJWT.Auth.AuthenticationResponse;
import com.Authentication.SpringSecurityJWT.Model.AuthenticationRequest;
import com.Authentication.SpringSecurityJWT.Model.RegisterRequest;

@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    private AuthService authService ;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        AuthenticationResponse authResponse = authService.register(registerRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate( @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authService.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
