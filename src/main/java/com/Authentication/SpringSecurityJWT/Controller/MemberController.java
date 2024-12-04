package com.Authentication.SpringSecurityJWT.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/management")
public class MemberController {

    // Only member can access this url end point.
    @GetMapping("/path")
    @PreAuthorize("hasAuthority('management:read')")
    public String getMember() {
        return "Secured endpoint: : GET Method - member controller only access by the Member only.";
    }
    
    // member and admin both can access this URL end point
    @PostMapping("/send")
    @PreAuthorize("hasAuthority('admin:create')")
    public String postMember() {
        return "Secured endpoint: : Post Method - member controller access by the Member && admin.";
    }
    

}
