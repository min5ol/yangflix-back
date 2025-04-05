package com.min5ol.back.Service;

import com.min5ol.back.Security.JwtTokenProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(UserDetails userDetails) {
        // userDetails에서 사용자 이름을 추출하여 토큰 생성
        return jwtTokenProvider.createToken(userDetails.getUsername());
    }
}
