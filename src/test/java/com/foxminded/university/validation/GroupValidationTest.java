package com.foxminded.university.validation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class GroupValidationTest {

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
        Group group = new Group(1, "zz-22");
        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidName() throws Exception {
        Group group = new Group(1, "zz-222");
        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Group> violation = violations.iterator().next();
        assertEquals("must match \"[a-zA-Z]{2}\\-\\d{2}\"", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
        assertEquals("zz-222", violation.getInvalidValue());
    }
    
    @Test
    public void shouldDetectInvalidStudentQuantity() throws Exception {
        Group group = new Group(1, "zz-22");
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            students.add(new Student(i, "John", "Doe", 22, group));
        }
        group.setStudents(students);
        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Group> violation = violations.iterator().next();
        assertEquals("size must be between 0 and 30", violation.getMessage());
        assertEquals("students", violation.getPropertyPath().toString());
        assertEquals(40, ((List<Student>)violation.getInvalidValue()).size());
    }
    
}
