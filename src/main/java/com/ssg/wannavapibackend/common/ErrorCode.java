package com.ssg.wannavapibackend.common;

public enum ErrorCode {
    EXAMPLE_ERROR(404, "index.css"),
    INVALID_VALUE(400, "Invalid value index.css error message️");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
