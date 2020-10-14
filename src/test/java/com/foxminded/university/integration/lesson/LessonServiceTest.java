package com.foxminded.university.integration.lesson;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lesson;
import com.foxminded.university.domain.Subject;
import com.foxminded.university.domain.Teacher;
import com.foxminded.university.integration.Util;
import com.foxminded.university.repository.LessonRepository;
import com.foxminded.university.service.LessonService;

@RunWith(SpringRunner.class)
public class LessonServiceTest {

    @TestConfiguration
    static class LessonServiceTestContextConfiguration{
        @Bean
        public LessonService lessonService() {
            return new LessonService();
        }
    }
    
    @Autowired
    private LessonService service;

    @MockBean
    private LessonRepository repository;

    @Before
    public void setUp() {
        Lesson lesson1 = new Lesson(1, new Classroom(1, "Class of Mathematics", 35), new Teacher(1, "Sam", "Clark", 33),
                new Subject(1, "Math"), new Group(1, "az-22"),
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 8, 0).getTime());
        Lesson lesson2 = new Lesson(2, new Classroom(2, "Class of Arts", 35), new Teacher(2, "Jack", "Bobson", 38),
                new Subject(2, "Arts"), new Group(2, "ff-11"),
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 9, 0).getTime());
        Lesson lesson3 = new Lesson(3, new Classroom(3, "Class of Physics", 29), new Teacher(3, "Ann", "Wilson", 33),
                new Subject(3, "Physics"), new Group(3, "hj-48"),
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 10, 0).getTime());
        List<Lesson> lessons = Arrays.asList(lesson1, lesson2, lesson3);

        Mockito.when(repository.findById(lesson1.getId())).thenReturn(Optional.of(lesson1));
        Mockito.when(repository.findById(lesson2.getId())).thenReturn(Optional.of(lesson2));
        Mockito.when(repository.findById(lesson3.getId())).thenReturn(Optional.of(lesson3));
        Mockito.when(repository.findAll()).thenReturn(lessons);
        Mockito.when(repository.findById(0)).thenReturn(Optional.empty());
    }

    @Test
    public void whenInvalidIdthenLessonShouldNotBeFound() {
        Lesson lesson = service.findLessonById(0);
        assertThat(lesson).isNull();
        Util.verifyFindByIdIsCalledOnce(repository);
    }

    @Test
    public void whenValidIdthenClassroomShouldBeFound() {
        Lesson lesson = service.findLessonById(1);
        assertThat(lesson.getId()).isEqualByComparingTo(1);
        Util.verifyFindByIdIsCalledOnce(repository);
    }
    
    @Test
    public void given3Classrooms_whengetAll_thenReturn3Records() {
        Lesson lesson1 = new Lesson(1, new Classroom(1, "Class of Mathematics", 35), new Teacher(1, "Sam", "Clark", 33),
                new Subject(1, "Math"), new Group(1, "az-22"),
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 8, 0).getTime());
        Lesson lesson2 = new Lesson(2, new Classroom(2, "Class of Arts", 35), new Teacher(2, "Jack", "Bobson", 38),
                new Subject(2, "Arts"), new Group(2, "ff-11"),
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 9, 0).getTime());
        Lesson lesson3 = new Lesson(3, new Classroom(3, "Class of Physics", 29), new Teacher(3, "Ann", "Wilson", 33),
                new Subject(3, "Physics"), new Group(3, "hj-48"),
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 10, 0).getTime());
        List<Lesson> lessons = service.findAllLessons();
        Util.verifyFindAllIsCalledOnce(repository);
        assertThat(lessons).hasSize(3).extracting(Lesson::getId).contains(lesson1.getId(), lesson2.getId(), lesson3.getId());
        assertThat(lessons).hasSize(3).extracting(Lesson::getClassroom).contains(lesson1.getClassroom(), lesson2.getClassroom(), lesson3.getClassroom());
        assertThat(lessons).hasSize(3).extracting(Lesson::getTeacher).contains(lesson1.getTeacher(), lesson2.getTeacher(), lesson3.getTeacher());
        assertThat(lessons).hasSize(3).extracting(Lesson::getSubject).contains(lesson1.getSubject(), lesson2.getSubject(), lesson3.getSubject());
        assertThat(lessons).hasSize(3).extracting(Lesson::getGroup).contains(lesson1.getGroup(), lesson2.getGroup(), lesson3.getGroup());
        
    }
    
}
