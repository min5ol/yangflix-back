package com.min5ol.back.exception;

// 이미 존재하는 아이디
public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException() {
        super("이미 사용 중인 아이디입니다.");
    }
}
