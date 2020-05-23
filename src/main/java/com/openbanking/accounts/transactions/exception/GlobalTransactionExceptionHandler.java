package com.openbanking.accounts.transactions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalTransactionExceptionHandler {

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Open Banking Service communication Exception")
    @ExceptionHandler(OpenBankSandboxServiceException.class)
    public void handleOpenBankSandboxServiceException (HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Incorrect SandBox InputDetail Exception")
    @ExceptionHandler(IncorrectSandBoxInputDetailException.class)
    public void handleIncorrectSandBoxInputDetailException (HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
    }

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Jwt validation exception")
    @ExceptionHandler(JwtValidationException.class)
    public void handleJwtValidationException (HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
    }
}
