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
import com.Authentication.SpringSecurityJWT.Auth.TokenBlacklistService;
import com.Authentication.SpringSecurityJWT.Model.AuthenticationRequest;
import com.Authentication.SpringSecurityJWT.Model.RegisterRequest;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// this is public URL end points that can access by anyone for registeration.
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    // Register the user
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        AuthenticationResponse authResponse = authService.register(registerRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    // user authentication while login
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        logger.info("Received Authentication request for email: {}", request.getEmail());
        AuthenticationResponse response = authService.authenticate(request);
        logger.info("Authentication done successfully for email: {}", request.getEmail());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // logout the user
    // Client-Side Logout Implementation : in javaScript localStorage.removeItem('token');
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        if (token != null) {
            tokenBlacklistService.blacklistToken(token);
        }
        return ResponseEntity.ok("Logged out successfully.");
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    
    }
}
