package com.min5ol.back.exception;

// 비밀번호 미입력
public class PasswordNotProvidedException extends RuntimeException {
    public PasswordNotProvidedException() {
        super("비밀번호를 입력해주세요.");
    }
}
