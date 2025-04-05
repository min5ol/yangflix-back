package com.min5ol.back.exception;

// 비밀번호 조건 미달성시(세부 메시지를 전달할 수 있도록 생성자 포함)
public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
