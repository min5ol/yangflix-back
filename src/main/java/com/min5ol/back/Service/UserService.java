package com.min5ol.back.Service;

import com.min5ol.back.DTO.SignUpRequest;
import com.min5ol.back.DTO.UserResponse;
import com.min5ol.back.Entity.User;
import com.min5ol.back.Repository.UserRepository;
import com.min5ol.back.exception.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getNickname(),
                user.getEmail(),
                user.getProfileImage()
        );
    }

    public void updateProfileImage(Long userId, String newImage) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
        user.setProfileImage(newImage);
        userRepository.save(user);
    }

    public void updateNickname(Long userId, String newNickname) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));

        if (!user.getNickname().equals(newNickname)) {
            if (userRepository.existsByNickname(newNickname)) {
                throw new DuplicateNicknameException();
            }
            user.setNickname(newNickname);
            userRepository.save(user);
        }
    }

    public boolean isEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean isNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new SignupUsernameNotProvidedException();
        }
        if (!username.matches("^[a-z0-9_-]{4,16}$")) {
            throw new InvalidSignupUsernameException();
        }
        if (userRepository.existsByUsername(username)) {
            throw new DuplicateUsernameException();
        }
    }

    public void validatePassword(String password, String confirmPassword) {
        if (password == null || password.trim().isEmpty()) {
            throw new PasswordNotProvidedException();
        }
        if (password.length() < 8 ||
                !password.matches(".*[a-zA-Z].*") ||
                !password.matches(".*[0-9!@#$%^&*].*")) {
            throw new InvalidPasswordException("비밀번호는 문자 1개, 숫자/특수문자 1개 이상 포함, 8자 이상이어야 합니다.");
        }
        if (!password.equals(confirmPassword)) {
            throw new PasswordMismatchException();
        }
    }

    public void validateNickname(String nickname) {
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new MissingNicknameException();
        }
        if (userRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }
    }

    public String signUp(SignUpRequest signUpRequest) {
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(encodedPassword)
                .nickname(signUpRequest.getNickname())
                .email(signUpRequest.getEmail())
                .role(User.Role.USER)
                .profileImage("https://res.cloudinary.com/dxavift7v/image/upload/v1742824631/profile-basic_k3dxhf.jpg")
                .build();

        userRepository.save(user);
        return "User registered successfully";
    }
}
