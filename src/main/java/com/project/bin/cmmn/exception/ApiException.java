package com.project.bin.cmmn.exception;

public class ApiException extends RuntimeException {
    private BaseExceptionType exceptionType;

    public ApiException(BaseExceptionType exceptionType) {
        super(exceptionType.getErrorMessage());
        this.exceptionType = exceptionType;
    }

    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
}
