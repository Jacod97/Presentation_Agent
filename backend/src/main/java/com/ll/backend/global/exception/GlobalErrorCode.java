package com.ll.backend.global.exception;

import org.springframework.http.HttpStatus;

public enum GlobalErrorCode {
    NOT_VALID(HttpStatus.BAD_REQUEST, "400", "요청이 올바르지 않습니다.");

    final HttpStatus status;
    final String code;
    final String message;

    GlobalErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
