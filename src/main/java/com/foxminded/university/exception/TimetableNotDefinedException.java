package com.foxminded.university.exception;

public class TimetableNotDefinedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TimetableNotDefinedException(String message) {
        super(message);
    }

}
