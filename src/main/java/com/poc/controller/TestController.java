package com.poc.controller;

import com.poc.controller.utils.PermissionsAndStatusUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/test")
public class TestController {

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize(PermissionsAndStatusUtils.AUTH_USER)
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize(PermissionsAndStatusUtils.AUTH_ADMIN)
    public String adminAccess() {
        return "Admin Board.";
    }

}
