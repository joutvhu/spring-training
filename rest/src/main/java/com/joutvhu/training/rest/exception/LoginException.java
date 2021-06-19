package com.joutvhu.training.rest.exception;

public class LoginException extends RuntimeException {
    private static final long serialVersionUID = -722974981576834231L;

    public LoginException(String message) {
        super(message);
    }
}
