package com.example.booking_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    UN_AUTHENTICATED(1001, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UN_AUTHORIZED(1002, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_KEY(1003, "Invalid message key", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(1004, "Username must be at least {min} character", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1005, "password must be al least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_DOB(1006, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1007, "User existed", HttpStatus.BAD_REQUEST),
    FOOTBALL_PITCHES_EXISTED(1008, "Football pitches existed", HttpStatus.BAD_REQUEST),

    USER_NOT_EXISTED(1009, "User not exists", HttpStatus.NOT_FOUND),
    FOOTBALL_PITCHES_NOT_EXISTED(1010, "Football pitches not exists", HttpStatus.NOT_FOUND),
    INCORRECT_ACCOUNT_OR_PASSWORD(1011, "Incorrect account or password", HttpStatus.NOT_FOUND),
    ;

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
