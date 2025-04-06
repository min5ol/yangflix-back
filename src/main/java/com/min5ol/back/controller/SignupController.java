package com.min5ol.back.controller;

import com.min5ol.back.DTO.SignUpRequest;
import com.min5ol.back.Service.UserService;
import com.min5ol.back.exception.DuplicateEmailException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/step1")
    public ResponseEntity<?> checkUsername(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        userService.validateUsername(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/step2")
    public ResponseEntity<?> checkPassword(@RequestBody Map<String, String> request) {
        String password = request.get("password");
        String confirmPassword = request.get("confirmPassword");
        userService.validatePassword(password, confirmPassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/step3")
    public ResponseEntity<?> checkNickname(@RequestBody Map<String, String> request) {
        String nickname = request.get("nickname");
        userService.validateNickname(nickname);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/step4")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        System.out.println("🔥 [STEP4] 요청 들어옴!");
        System.out.println("▶ username: " + signUpRequest.getUsername());
        System.out.println("▶ password: " + signUpRequest.getPassword());
        System.out.println("▶ nickname: " + signUpRequest.getNickname());
        System.out.println("▶ email: " + signUpRequest.getEmail());
    
        if (userService.isEmailDuplicate(signUpRequest.getEmail())) {
            throw new DuplicateEmailException();
        }
    
        userService.signUp(signUpRequest);
        return ResponseEntity.ok().build();
    }
}
