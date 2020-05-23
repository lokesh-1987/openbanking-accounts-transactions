package com.openbanking.accounts.transactions.exception;

public class JwtMissingException extends RuntimeException {

    public JwtMissingException(String message) {
        super(message);
    }
}
