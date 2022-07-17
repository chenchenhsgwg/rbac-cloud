package com.bosssoft.trainee.base.common.exception;

import com.bosssoft.trainee.base.common.enums.AuthenticationEnums;

public class AuthenticationException extends BaseException {

    public AuthenticationException() {
        super(AuthenticationEnums.AUTH_FAIL.getValue());
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, int status) {
        super(message, status);
    }
}