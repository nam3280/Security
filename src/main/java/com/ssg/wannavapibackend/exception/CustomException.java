package com.ssg.wannavapibackend.exception;

import com.ssg.wannavapibackend.common.ErrorCode;

public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}