package com.foxminded.university.integration.teacher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.foxminded.university.domain.Teacher;
import com.foxminded.university.repository.TeacherRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeacherRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TeacherRepository repository;

    @Test
    public void whenFindByIdThenReturnTeacher() {
        Teacher expected = new Teacher(1, "Jake", "Eagle", 58);
        entityManager.persistAndFlush(expected);

        Teacher actual = repository.findById(1).get();
        assertEquals(expected, actual);
    }

    @Test
    public void whenInvalidIdThenReturnNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            repository.findById(0).get();
        });
    }
    
    @Test
    public void givenListOfTeachersWhenFindAllThenReturnAllTeachers() {
        Teacher teacher1 = new Teacher(1, "Michael", "Jackson", 42);
        Teacher teacher2 = new Teacher(2, "Sam", "Parkinson", 42);
        Teacher teacher3 = new Teacher(3, "John", "Muller", 42);
        List<Teacher> expectedTeachers = Arrays.asList(teacher1, teacher2, teacher3);
        
        entityManager.persist(teacher1);
        entityManager.persist(teacher2);
        entityManager.persist(teacher3);
        entityManager.flush();
        
        List<Teacher> actualTeachers = repository.findAll();
        
        assertEquals(expectedTeachers, actualTeachers);
    }

}
