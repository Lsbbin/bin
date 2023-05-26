package com.project.bin.dto.enums;


import com.project.bin.cmmn.exception.BaseExceptionType;

public enum ExceptionType implements BaseExceptionType {

    SUCCESS(200, 200, "SUCCESS"),
    DATA_NOT_FOUND(204, 204, "DATA NOT FOUND"),
    BAD_REQUEST(400, 400, "BAD REQUEST"),
    NO_AUTHORITY(401, 401, "NO AUTHORITY"),
    SERVER_ERROR(500, 500, "SERVER_ERROR");

    private final int errorCode;
    private int httpStatus;
    private String errorMessage;

    ExceptionType(int errorCode, int httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public int getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

}
