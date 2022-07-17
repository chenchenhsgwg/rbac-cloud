package com.bosssoft.trainee.base.core.exception;

import com.bosssoft.trainee.base.common.exception.AuthenticationException;
import com.bosssoft.trainee.base.common.exception.BusinessException;
import com.bosssoft.trainee.base.common.exception.LoginException;
import com.bosssoft.trainee.base.core.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AuthenticationException.class)
    public Result authenticationHandler(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws Exception {
        response.setStatus(401);
        return new Result(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(value = LoginException.class)
    public Result loginExceptionHandler(HttpServletRequest request, HttpServletResponse response, LoginException e) throws Exception {
        response.setStatus(403);
        return new Result(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    public Result businessExceptionHandler(HttpServletRequest request, HttpServletResponse response, BusinessException e) throws Exception {
        response.setStatus(200);
        return new Result(e.getStatus(), e.getMessage());
    }
}