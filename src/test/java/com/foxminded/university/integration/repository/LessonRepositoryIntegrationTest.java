package com.foxminded.university.integration.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lesson;
import com.foxminded.university.domain.Subject;
import com.foxminded.university.domain.Teacher;
import com.foxminded.university.repository.LessonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LessonRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LessonRepository repository;

    @Test
    public void whenFindByIdThenReturnLesson() {
        Classroom classroom = new Classroom(1, "Class of Mathematics", 35);
        Teacher teacher = new Teacher(1, "Sam", "Clark", 33);
        Subject subject = new Subject(1, "Math");
        Group group = new Group(1, "az-22");
        Lesson expected = new Lesson(1, classroom, teacher, subject, group,
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 8, 0).getTime());
        entityManager.persist(classroom);
        entityManager.persist(teacher);
        entityManager.persist(subject);
        entityManager.persist(group);
        entityManager.persist(expected);
        entityManager.flush();

        Lesson actual = repository.findById(1).get();
        assertEquals(expected, actual);
    }

    @Test
    public void whenInvalidIdThenReturnNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            repository.findById(0).get();
        });
    }

    @Test
    public void givenListOfLessonsWhenFindAllThenReturnAllLessons() {
        Classroom classroom1 = new Classroom(1, "Class of Mathematics", 35);
        Teacher teacher1 = new Teacher(1, "Sam", "Clark", 33);
        Subject subject1 = new Subject(1, "Math");
        Group group1 = new Group(1, "az-22");
        Lesson lesson1 = new Lesson(1, classroom1, teacher1, subject1, group1,
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 8, 0).getTime());

        Classroom classroom2 = new Classroom(2, "Class of Arts", 24);
        Teacher teacher2 = new Teacher(2, "Jack", "Lee", 33);
        Subject subject2 = new Subject(2, "Arts");
        Group group2 = new Group(2, "ff-34");
        Lesson lesson2 = new Lesson(2, classroom2, teacher2, subject2, group2,
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 9, 0).getTime());

        Classroom classroom3 = new Classroom(3, "Class of Engineering", 40);
        Teacher teacher3 = new Teacher(3, "Bob", "Dylan", 42);
        Subject subject3 = new Subject(3, "Astronomy");
        Group group3 = new Group(3, "aa-93");
        Lesson lesson3 = new Lesson(3, classroom3, teacher3, subject3, group3,
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 10, 0).getTime());
        
        List<Lesson> expectedLessons = Arrays.asList(lesson1, lesson2, lesson3);

        entityManager.persist(classroom1);
        entityManager.persist(classroom2);
        entityManager.persist(classroom3);
        entityManager.persist(teacher1);
        entityManager.persist(teacher2);
        entityManager.persist(teacher3);
        entityManager.persist(subject1);
        entityManager.persist(subject2);
        entityManager.persist(subject3);
        entityManager.persist(group1);
        entityManager.persist(group2);
        entityManager.persist(group3);
        entityManager.persist(lesson1);
        entityManager.persist(lesson2);
        entityManager.persist(lesson3);
        entityManager.flush();

        List<Lesson> actualLessons = repository.findAll();

        assertEquals(expectedLessons, actualLessons);
    }

}
