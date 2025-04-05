package com.min5ol.back.controller;

import com.min5ol.back.DTO.LoginRequest;
import com.min5ol.back.DTO.LoginResponse;
import com.min5ol.back.Entity.User;
import com.min5ol.back.Repository.UserRepository;
import com.min5ol.back.Security.JwtTokenProvider;
import com.min5ol.back.exception.InvalidCredentialsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public LoginController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(InvalidCredentialsException::new);

        String token = jwtTokenProvider.createToken(username);

        return new LoginResponse(
                token,
                user.getId(),
                user.getUsername(),
                user.getRole().name()
        );
    }
}
