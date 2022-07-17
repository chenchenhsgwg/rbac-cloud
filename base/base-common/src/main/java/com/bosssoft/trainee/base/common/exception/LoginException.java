package com.bosssoft.trainee.base.common.exception;

import com.bosssoft.trainee.base.common.enums.AuthenticationEnums;

public class LoginException extends BaseException {

    public LoginException() {
        super(AuthenticationEnums.UNKNOWN_ACCOUNT.getValue());
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, int status) {
        super(message, status);
    }
}