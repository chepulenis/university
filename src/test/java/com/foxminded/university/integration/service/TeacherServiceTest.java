package com.foxminded.university.integration.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
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

import com.foxminded.university.domain.Teacher;
import com.foxminded.university.integration.Util;
import com.foxminded.university.repository.TeacherRepository;
import com.foxminded.university.service.TeacherService;

@RunWith(SpringRunner.class)
public class TeacherServiceTest {

    @TestConfiguration
    static class TeacherServiceTestContextConfiguration {
        @Bean
        public TeacherService teacherService() {
            return new TeacherService();
        }
    }

    @Autowired
    private TeacherService service;

    @MockBean
    private TeacherRepository repository;

    @Before
    public void setUp() {
        Teacher teacher1 = new Teacher(1, "Jack", "Robinson", 33);
        Teacher teacher2 = new Teacher(1, "Jacob", "Walker", 29);
        Teacher teacher3 = new Teacher(1, "James", "Allen", 44);
        List<Teacher> teachers = Arrays.asList(teacher1, teacher2, teacher3);

        Mockito.when(repository.findById(teacher1.getId())).thenReturn(Optional.of(teacher1));
        Mockito.when(repository.findById(teacher2.getId())).thenReturn(Optional.of(teacher2));
        Mockito.when(repository.findById(teacher3.getId())).thenReturn(Optional.of(teacher3));
        Mockito.when(repository.findAll()).thenReturn(teachers);
        Mockito.when(repository.findById(0)).thenReturn(Optional.empty());
    }

    @Test
    public void whenInvalidIdthenTeacherShouldNotBeFound() {
        Teacher teacher = service.findTeacherById(0);
        assertThat(teacher).isNull();
        Util.verifyFindByIdIsCalledOnce(repository);
    }

    @Test
    public void whenValidIdthenTeacherShouldBeFound() {
        Teacher teacher = service.findTeacherById(1);
        assertThat(teacher.getId()).isEqualByComparingTo(1);
        Util.verifyFindByIdIsCalledOnce(repository);
    }

    @Test
    public void given3Classrooms_whengetAll_thenReturn3Records() {
        Teacher teacher1 = new Teacher(1, "Jack", "Robinson", 33);
        Teacher teacher2 = new Teacher(1, "Jacob", "Walker", 29);
        Teacher teacher3 = new Teacher(1, "James", "Allen", 44);
        List<Teacher> teachers = service.findAllTeachers();
        Util.verifyFindAllIsCalledOnce(repository);
        assertThat(teachers).hasSize(3).extracting(Teacher::getId).contains(teacher1.getId(), teacher2.getId(),
                teacher3.getId());
        assertThat(teachers).hasSize(3).extracting(Teacher::getFirstName).contains(teacher1.getFirstName(),
                teacher2.getFirstName(), teacher3.getFirstName());
        assertThat(teachers).hasSize(3).extracting(Teacher::getLastName).contains(teacher1.getLastName(),
                teacher2.getLastName(), teacher3.getLastName());
        assertThat(teachers).hasSize(3).extracting(Teacher::getAge).contains(teacher1.getAge(), teacher2.getAge(),
                teacher3.getAge());

    }

}
