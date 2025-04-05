package com.min5ol.back.exception;

// 이미 존재하는 아이디
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super("이미 사용 중인 이메일입니다.");
    }
}
