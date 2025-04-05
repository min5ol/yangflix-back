package com.min5ol.back.exception;

// 아이디 미입력
public class UsernameNotProvidedException extends RuntimeException {
    public UsernameNotProvidedException() {
        super("아이디를 입력해주세요.");
    }
}
