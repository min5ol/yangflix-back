package com.min5ol.back.exception;

// 회원가입 시 아이디 미입력
public class SignupUsernameNotProvidedException extends RuntimeException {
    public SignupUsernameNotProvidedException() {
        super("아이디를 입력해주세요.");
    }
}
