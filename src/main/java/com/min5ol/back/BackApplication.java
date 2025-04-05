package com.min5ol.back;

import com.min5ol.back.Entity.User;
import com.min5ol.back.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder; // 비밀번호 암호화용

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Yangflix API",
        version = "v1",
        description = "Yangflix API Documentation"
    )
)

@SpringBootApplication
public class BackApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

    // ✅ 관리자 계정 자동 생성 코드
    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = "sol0508@kakao.com";
            String adminUsername = "kkulbbannxx";
            String adminPassword = "wkdalsthf1!";
    
            if (!userRepository.existsByEmail(adminEmail)) {
                User admin = User.builder()
                        .username(adminUsername)
                        .password(passwordEncoder.encode(adminPassword))
                        .email(adminEmail)
                        .nickname("꿀빵이")
                        .role(User.Role.ADMIN)
                        .profileImage("https://res.cloudinary.com/dxavift7v/image/upload/v1742824631/profile-basic_k3dxhf.jpg")
                        .build();
                userRepository.save(admin);
                System.out.println("✅ 관리자 계정 생성 완료");
            } else {
                System.out.println("ℹ️ 관리자 계정 이미 존재함");
            }
        };
    }
    
}
