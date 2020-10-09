package com.foxminded.university.integration;

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

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.repository.ClassroomRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClassroomRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClassroomRepository repository;

    @Test
    public void whenFindByIdThenReturnClassroom() {
        Classroom expected = new Classroom(1, "Class of Mathematics", 28);
        entityManager.persistAndFlush(expected);

        Classroom actual = repository.findById(1).get();
        assertEquals(expected, actual);
    }

    @Test
    public void whenInvalidIdThenReturnNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            repository.findById(0).get();
        });
    }
    
    @Test
    public void givenListOfClassroomsWhenFindAllThenReturnAllClassrooms() {
        Classroom english = new Classroom(1, "Class of English", 28);
        Classroom math = new Classroom(2, "Class of Mathematics", 25);
        Classroom physics = new Classroom(3, "Class of Physics", 26);
        List<Classroom> expectedClassrooms = Arrays.asList(english, math, physics);
        
        entityManager.persist(english);
        entityManager.persist(math);
        entityManager.persist(physics);
        entityManager.flush();
        
        List<Classroom> actualClassrooms = repository.findAll();
        
        assertEquals(expectedClassrooms, actualClassrooms);
    }

}
