package com.min5ol.back.exception;

// GlobalExceptionHandler.java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 로그인 예외 처리
    @ExceptionHandler(UsernameNotProvidedException.class)
    public ResponseEntity<ErrorDetails> handleUsernameNotProvided(UsernameNotProvidedException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotProvidedException.class)
    public ResponseEntity<ErrorDetails> handlePasswordNotProvided(PasswordNotProvidedException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleInvalidCredentials(InvalidCredentialsException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(LoginServerException.class)
    public ResponseEntity<ErrorDetails> handleLoginServerError(LoginServerException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 회원가입 예외 처리
    @ExceptionHandler(SignupUsernameNotProvidedException.class)
    public ResponseEntity<ErrorDetails> handleSignupUsernameNotProvided(SignupUsernameNotProvidedException ex,
            WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidSignupUsernameException.class)
    public ResponseEntity<ErrorDetails> handleInvalidSignupUsername(InvalidSignupUsernameException ex,
            WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateUsername(DuplicateUsernameException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.CONFLICT.value(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorDetails> handleInvalidPassword(InvalidPasswordException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorDetails> handlePasswordMismatch(PasswordMismatchException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingNicknameException.class)
    public ResponseEntity<ErrorDetails> handleMissingNickname(MissingNicknameException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateNicknameException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateNickname(DuplicateNicknameException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.CONFLICT.value(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // GlobalExceptionHandler.java
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateEmail(DuplicateEmailException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.CONFLICT.value(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // 나머지 엔티티들에 대해 단순 "ok" 또는 서버 오류 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 오류",
                request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
