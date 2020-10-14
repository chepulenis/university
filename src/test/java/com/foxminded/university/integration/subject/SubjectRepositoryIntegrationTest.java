package com.foxminded.university.integration.subject;

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

import com.foxminded.university.domain.Subject;
import com.foxminded.university.repository.SubjectRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SubjectRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SubjectRepository repository;

    @Test
    public void whenFindByIdThenReturnSubject() {
        Subject expected = new Subject(1, "Mathematics");
        entityManager.persistAndFlush(expected);

        Subject actual = repository.findById(1).get();
        assertEquals(expected, actual);
    }

    @Test
    public void whenInvalidIdThenReturnNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            repository.findById(0).get();
        });
    }
    
    @Test
    public void givenListOfSubjectsWhenFindAllThenReturnAllSubjects() {
        Subject english = new Subject(1, "English");
        Subject math = new Subject(2, "Mathematics");
        Subject physics = new Subject(3, "Physics");
        List<Subject> expectedSubjects = Arrays.asList(english, math, physics);
        
        entityManager.persist(english);
        entityManager.persist(math);
        entityManager.persist(physics);
        entityManager.flush();
        
        List<Subject> actualSubjects = repository.findAll();
        
        assertEquals(expectedSubjects, actualSubjects);
    }

}
