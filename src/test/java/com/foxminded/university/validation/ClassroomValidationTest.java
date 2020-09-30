package com.foxminded.university.validation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.foxminded.university.domain.Classroom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ClassroomValidationTest {

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
        Classroom classroom = new Classroom(1, "Class of Mathematics", 30);
        Set<ConstraintViolation<Classroom>> violations = validator.validate(classroom);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidName() throws Exception {
        Classroom classroom = new Classroom(1, "a", 30);
        Set<ConstraintViolation<Classroom>> violations = validator.validate(classroom);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Classroom> violation = violations.iterator().next();
        assertEquals("size must be between 2 and 40", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
        assertEquals("a", violation.getInvalidValue());
    }
    
    @Test
    public void shouldDetectInvalidLargerSize() throws Exception {
        Classroom classroom = new Classroom(1, "Class of Mathematics", 500);
        Set<ConstraintViolation<Classroom>> violations = validator.validate(classroom);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Classroom> violation = violations.iterator().next();
        assertEquals("must be less than or equal to 80", violation.getMessage());
        assertEquals("size", violation.getPropertyPath().toString());
        assertEquals(500, violation.getInvalidValue());
    }
    
    @Test
    public void shouldDetectInvalidLesserSize() throws Exception {
        Classroom classroom = new Classroom(1, "Class of Mathematics", 3);
        Set<ConstraintViolation<Classroom>> violations = validator.validate(classroom);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Classroom> violation = violations.iterator().next();
        System.out.println(violation.getMessage());
        assertEquals("must be greater than or equal to 10", violation.getMessage());
        assertEquals("size", violation.getPropertyPath().toString());
        assertEquals(3, violation.getInvalidValue());
    }

}
