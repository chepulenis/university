package com.foxminded.university.integration.student;

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

import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;
import com.foxminded.university.integration.Util;
import com.foxminded.university.repository.StudentRepository;
import com.foxminded.university.service.StudentService;

@RunWith(SpringRunner.class)
public class StudentServiceTest {

    @TestConfiguration
    static class StudentServiceTestContextConfiguration {
        @Bean
        public StudentService studentService() {
            return new StudentService();
        }
    }

    @Autowired
    private StudentService service;

    @MockBean
    private StudentRepository repository;

    @Before
    public void setUp() {
        Student student1 = new Student(1, "John", "Doe", 23, new Group(1, "sx-32"));
        Student student2 = new Student(2, "Jane", "Wilson", 19, new Group(2, "ab-12"));
        Student student3 = new Student(3, "Robert", "Smith", 22, new Group(3, "vg-11"));
        List<Student> students = Arrays.asList(student1, student2, student3);

        Mockito.when(repository.findById(student1.getId())).thenReturn(Optional.of(student1));
        Mockito.when(repository.findById(student2.getId())).thenReturn(Optional.of(student2));
        Mockito.when(repository.findById(student3.getId())).thenReturn(Optional.of(student3));
        Mockito.when(repository.findAll()).thenReturn(students);
        Mockito.when(repository.findById(0)).thenReturn(Optional.empty());
    }

    @Test
    public void whenInvalidIdthenStudentShouldNotBeFound() {
        Student student = service.findStudentById(0);
        assertThat(student).isNull();
        Util.verifyFindByIdIsCalledOnce(repository);
    }

    @Test
    public void whenValidIdthenStudentShouldBeFound() {
        Student student = service.findStudentById(1);
        assertThat(student.getId()).isEqualByComparingTo(1);
        Util.verifyFindByIdIsCalledOnce(repository);
    }

    @Test
    public void given3Students_whengetAll_thenReturn3Records() {
        Student student1 = new Student(1, "John", "Doe", 23, new Group(1, "sx-32"));
        Student student2 = new Student(2, "Jane", "Wilson", 19, new Group(2, "ab-12"));
        Student student3 = new Student(3, "Robert", "Smith", 22, new Group(3, "vg-11"));
        List<Student> students = service.findAllStudents();
        Util.verifyFindAllIsCalledOnce(repository);
        assertThat(students).hasSize(3).extracting(Student::getId).contains(student1.getId(), student2.getId(),
                student3.getId());
        assertThat(students).hasSize(3).extracting(Student::getFirstName).contains(student1.getFirstName(),
                student2.getFirstName(), student3.getFirstName());
        assertThat(students).hasSize(3).extracting(Student::getLastName).contains(student1.getLastName(),
                student2.getLastName(), student3.getLastName());
        assertThat(students).hasSize(3).extracting(Student::getAge).contains(student1.getAge(), student2.getAge(),
                student3.getAge());
        assertThat(students).hasSize(3).extracting(Student::getGroup).contains(student1.getGroup(), student2.getGroup(),
                student3.getGroup());

    }
    
}
