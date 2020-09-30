package com.foxminded.university.validation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.foxminded.university.domain.Teacher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class TeacherValidationTest {

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
        Teacher teacher = new Teacher(1, "John", "Doe", 50);
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidFirstName() throws Exception {
        Teacher teacher = new Teacher(1, "a", "Doe", 50);
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Teacher> violation = violations.iterator().next();
        assertEquals("size must be between 2 and 40", violation.getMessage());
        assertEquals("firstName", violation.getPropertyPath().toString());
        assertEquals("a", violation.getInvalidValue());
    }
    
    @Test
    public void shouldDetectInvalidLastName() throws Exception {
        Teacher teacher = new Teacher(1, "John", "a", 50);
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Teacher> violation = violations.iterator().next();
        assertEquals("size must be between 2 and 40", violation.getMessage());
        assertEquals("lastName", violation.getPropertyPath().toString());
        assertEquals("a", violation.getInvalidValue());
    }
    
    @Test
    public void shouldDetectInvalidLargeAge() throws Exception {
        Teacher teacher = new Teacher(1, "John", "Doe", 6666);
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Teacher> violation = violations.iterator().next();
        System.out.println(violation.getMessage());
        assertEquals("numeric value out of bounds (<3 digits>.<0 digits> expected)", violation.getMessage());
        assertEquals("age", violation.getPropertyPath().toString());
        assertEquals(6666, violation.getInvalidValue());
    }
    
    @Test
    public void shouldDetectInvalidNegativeAge() throws Exception {
        Teacher teacher = new Teacher(1, "John", "Doe", -50);
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Teacher> violation = violations.iterator().next();
        System.out.println(violation.getMessage());
        assertEquals("must be greater than 0", violation.getMessage());
        assertEquals("age", violation.getPropertyPath().toString());
        assertEquals(-50, violation.getInvalidValue());
    }

}
