package com.foxminded.exception;

public class TimetableNotDefinedException extends Exception {
    private static final long serialVersionUID = 1L;

    public TimetableNotDefinedException(String message) {
        super(message);
    }

}
