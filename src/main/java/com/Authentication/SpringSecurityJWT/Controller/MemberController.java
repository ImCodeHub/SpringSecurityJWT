package com.Authentication.SpringSecurityJWT.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/v1/management")
public class MemberController {
    @GetMapping("/path")
    public String getMember() {
        return "Secured endpoint: : GET - member controller.";
    }

}
