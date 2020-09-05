package com.common.exception;

import org.springframework.http.HttpStatus;

public class MyException extends RuntimeException {

    private HttpStatus status;

    private String errorCode;

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public MyException() {
        this(HttpStatus.INTERNAL_SERVER_ERROR, "500", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    public MyException(String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, "500", message);
    }

    public MyException(String errorCode , String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, errorCode, message);
    }

    public MyException(HttpStatus status, String errorCode, String message) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    public MyException(HttpStatus status,  String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.errorCode = errorCode;
    }
}
