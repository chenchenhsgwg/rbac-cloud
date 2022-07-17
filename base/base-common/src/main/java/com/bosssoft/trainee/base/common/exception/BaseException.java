package com.bosssoft.trainee.base.common.exception;

import com.bosssoft.trainee.base.common.constant.HttpStatusConstant;

public class BaseException extends RuntimeException {
    private int status = HttpStatusConstant.FAIL;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}