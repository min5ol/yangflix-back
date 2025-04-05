package com.min5ol.back.exception;

// 비밀번호 확인 불일치
public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
