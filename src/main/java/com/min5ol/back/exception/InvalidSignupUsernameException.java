package com.min5ol.back.exception;

// 아이디 형식이 조건에 맞지 않을 경우
public class InvalidSignupUsernameException extends RuntimeException {
    public InvalidSignupUsernameException() {
        super("아이디는 영소문자 및 숫자를 포함하여야 합니다.");
    }
}
