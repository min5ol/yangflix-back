package com.min5ol.back.exception;

// 아이디 또는 비밀번호가 틀렸을 경우
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
    }
}
