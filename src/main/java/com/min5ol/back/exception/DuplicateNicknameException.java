package com.min5ol.back.exception;

// 이미 존재하는 닉네임
public class DuplicateNicknameException extends RuntimeException {
    public DuplicateNicknameException() {
        super("이미 사용중인 닉네임입니다.");
    }
}
