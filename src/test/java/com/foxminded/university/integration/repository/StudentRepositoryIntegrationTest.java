package com.foxminded.university.integration.repository;

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

import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;
import com.foxminded.university.repository.StudentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository repository;

    @Test
    public void whenFindByIdThenReturnStudent() {
        Group group = new Group(1, "zz-55");
        Student expected = new Student (1, "John", "Doe", 23, group);
        entityManager.persist(group);
        entityManager.persist(expected);
        entityManager.flush();

        Student actual = repository.findById(1).get();
        assertEquals(expected, actual);
    }

    @Test
    public void whenInvalidIdThenReturnNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            repository.findById(0).get();
        });
    }
    
    @Test
    public void givenListOfStudentsWhenFindAllThenReturnAllStudents() {
        Group group1 = new Group(1, "zz-55");
        Group group2 = new Group(2, "ax-23");
        Group group3 = new Group(3, "cd-52");
        Student student1 = new Student (1, "John", "Doe", 23, group1);
        Student student2 = new Student (2, "Jane", "Doe", 19, group2);
        Student student3 = new Student (3, "Mick", "Jagger", 25, group3);
        List<Student> expectedStudents = Arrays.asList(student1, student2, student3);
        
        entityManager.persist(group1);
        entityManager.persist(group2);
        entityManager.persist(group3);
        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.flush();
        
        List<Student> actualStudents = repository.findAll();
        
        assertEquals(expectedStudents, actualStudents);
    }

}
