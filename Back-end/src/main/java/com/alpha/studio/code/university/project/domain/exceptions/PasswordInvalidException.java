package com.alpha.studio.code.university.project.domain.exceptions;

public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException() {
        super("Invalid password.");
    }
}
