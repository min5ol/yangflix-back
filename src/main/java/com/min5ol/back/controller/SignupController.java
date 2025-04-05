package com.min5ol.back.controller;

import com.min5ol.back.DTO.SignUpRequest;
import com.min5ol.back.Service.UserService;
import com.min5ol.back.exception.DuplicateEmailException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/step1")
    public ResponseEntity<Map<String, String>> checkUsername(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        userService.validateUsername(username);

        Map<String, String> response = new HashMap<>();
        response.put("message", "사용 가능한 아이디입니다.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/step2")
    public ResponseEntity<Map<String, String>> checkPassword(@RequestBody Map<String, String> request) {
        String password = request.get("password");
        String confirmPassword = request.get("confirmPassword");
        userService.validatePassword(password, confirmPassword);

        Map<String, String> response = new HashMap<>();
        response.put("message", "사용 가능한 비밀번호입니다.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/step3")
    public ResponseEntity<Map<String, String>> checkNickname(@RequestBody Map<String, String> request) {
        String nickname = request.get("nickname");
        userService.validateNickname(nickname);

        Map<String, String> response = new HashMap<>();
        response.put("message", "사용 가능한 닉네임입니다.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/step4")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userService.isEmailDuplicate(signUpRequest.getEmail())) {
            throw new DuplicateEmailException();
        }

        userService.signUp(signUpRequest);

        Map<String, String> response = new HashMap<>();
        response.put("message", "회원가입이 완료되었습니다.");
        return ResponseEntity.ok(response);
    }
}
