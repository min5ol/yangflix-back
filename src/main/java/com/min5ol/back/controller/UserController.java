package com.min5ol.back.controller;

import com.min5ol.back.DTO.UserResponse;
import com.min5ol.back.Security.CustomUserDetails;
import com.min5ol.back.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserResponse getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userService.getUserById(userDetails.getUser().getId());
    }

    @PatchMapping("/profile/image")
    public ResponseEntity<Map<String, String>> updateProfileImage(
            @RequestBody Map<String, String> request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        String newImage = request.get("profileImage");
        userService.updateProfileImage(userDetails.getUser().getId(), newImage);

        Map<String, String> response = new HashMap<>();
        response.put("message", "프로필 이미지가 변경되었습니다.");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/nickname")
    public ResponseEntity<Map<String, String>> updateNickname(
            @RequestBody Map<String, String> request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        String newNickname = request.get("nickname");
        userService.updateNickname(userDetails.getUser().getId(), newNickname);

        Map<String, String> response = new HashMap<>();
        response.put("message", "닉네임이 변경되었습니다.");
        return ResponseEntity.ok(response);
    }
}
