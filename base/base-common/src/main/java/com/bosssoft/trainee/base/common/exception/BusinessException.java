package com.bosssoft.trainee.base.common.exception;

public class BusinessException extends BaseException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, int status) {
        super(message, status);
    }
}