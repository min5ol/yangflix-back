package com.min5ol.back; // 프로젝트의 최상위 패키지

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 애플리케이션 실행
    }
}
