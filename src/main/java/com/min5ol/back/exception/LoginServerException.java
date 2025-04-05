package com.min5ol.back.exception;

// 서버 오류 발생 시 로그인 관련 예외
public class LoginServerException extends RuntimeException {
    public LoginServerException() {
        super("일시적인 오류로 로그인 할 수 없습니다. 잠시후 다시 이용해주세요.");
    }
}
