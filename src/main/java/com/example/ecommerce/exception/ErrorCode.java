package com.example.ecommerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

    USER_EXISTED(1001, HttpStatus.NOT_ACCEPTABLE,"Customer with this email is already existed!"),
    USER_NOT_FOUND(1002, HttpStatus.NOT_FOUND, "This account does not existed!"),
    CATE_EXISTED(1003,HttpStatus.NOT_ACCEPTABLE,"This category is already existed"),
    CATE_NOT_FOUND(1004,HttpStatus.NOT_FOUND,"This category does not existed!")
    ;

    private final int code;
    private HttpStatusCode statusCode;
    private String message;

    ErrorCode(int code,HttpStatusCode statusCode, String message) {
        this.code = code;
        this.statusCode = statusCode;
        this.message = message;
    }
}
