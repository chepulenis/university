package com.foxminded.university.controller;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.foxminded.university.domain.Subject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class SubjectValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void shouldHaveNoViolations() throws Exception {
        Subject subject = new Subject(1, "Mathematics");
        Set<ConstraintViolation<Subject>> violations = validator.validate(subject);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidLargeName() throws Exception {
        Subject subject = new Subject(1, "Mathematicsssssssssssssssssssssssssssssssssssssssss");
        Set<ConstraintViolation<Subject>> violations = validator.validate(subject);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Subject> violation = violations.iterator().next();
        assertEquals("size must be between 2 and 30", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
        assertEquals("Mathematicsssssssssssssssssssssssssssssssssssssssss", violation.getInvalidValue());
    }
    
    @Test
    public void shouldDetectInvalidSmallSize() throws Exception {
        Subject subject = new Subject(1, "a");
        Set<ConstraintViolation<Subject>> violations = validator.validate(subject);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Subject> violation = violations.iterator().next();
        assertEquals("size must be between 2 and 30", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
        assertEquals("a", violation.getInvalidValue());
    }

}
