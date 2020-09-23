package com.foxminded.university.exception;

public class StudentNotAssignedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final String ERR_MESSAGE = "Student with name %s %s is not assigned to a group";

    public StudentNotAssignedException(String firstName, String lastName) {
        super(String.format(ERR_MESSAGE, firstName, lastName));
    }

}
