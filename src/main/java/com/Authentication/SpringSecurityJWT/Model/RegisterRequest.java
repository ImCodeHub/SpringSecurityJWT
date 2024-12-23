package com.Authentication.SpringSecurityJWT.Model;

import com.Authentication.SpringSecurityJWT.Entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// user registration model
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String password;

    private Role role;

}
