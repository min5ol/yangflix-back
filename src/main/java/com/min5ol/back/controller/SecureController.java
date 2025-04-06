package com.min5ol.back.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/api/secure-data")
    public String getSecureData(@AuthenticationPrincipal String username) {
        return "This is secured data for " + username;
    }
}
