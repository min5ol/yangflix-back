package com.min5ol.back.exception;

// 닉네임 미입력
public class MissingNicknameException extends RuntimeException {
    public MissingNicknameException() {
        super("이름을 입력해주세요.");
    }
}
