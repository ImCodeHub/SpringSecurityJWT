package com.Authentication.SpringSecurityJWT.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping("/file")
    @PreAuthorize("hasAuthority('admin:read')")
    public String getAdmin() {
        return "Secured endpoint: : GET - Admin controller.";
    }

}
