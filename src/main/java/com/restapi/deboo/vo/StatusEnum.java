package com.restapi.deboo.vo;

public enum StatusEnum {
    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERVER_ERROR(50, "INTERNAL_SERVER_ERROR"),
    ALREADY_REPORTED(901, "ALREADY_REPORTED");

    int statusCode;
    String code;

    StatusEnum(int statudCode, String code) {
        this.statusCode = statudCode;
        this.code = code;
    }
}
