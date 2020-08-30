package com.foxminded.exception;

public class StudentNotAssignedException extends Exception {
    private static final long serialVersionUID = 1L;

    public StudentNotAssignedException(String message) {
        super(message);
    }

}
