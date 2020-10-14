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

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.integration.Util;
import com.foxminded.university.repository.ClassroomRepository;
import com.foxminded.university.service.ClassroomService;

@RunWith(SpringRunner.class)
public class SubjectServiceTest {

    @TestConfiguration
    static class ClassroomServiceTestContextConfiguration{
        @Bean
        public ClassroomService classroomService() {
            return new ClassroomService();
        }
    }
    
    @Autowired
    private ClassroomService service;

    @MockBean
    private ClassroomRepository repository;

    @Before
    public void setUp() {
        Classroom classroom1 = new Classroom(1, "Class of Phyics", 35);
        Classroom classroom2 = new Classroom(2, "Class of Engineering", 40);
        Classroom classroom3 = new Classroom(3, "Class of Phyics", 26);
        List<Classroom> classrooms = Arrays.asList(classroom1, classroom2, classroom3);

        Mockito.when(repository.findById(classroom1.getId())).thenReturn(Optional.of(classroom1));
        Mockito.when(repository.findById(classroom2.getId())).thenReturn(Optional.of(classroom2));
        Mockito.when(repository.findById(classroom3.getId())).thenReturn(Optional.of(classroom3));
        Mockito.when(repository.findAll()).thenReturn(classrooms);
        Mockito.when(repository.findById(0)).thenReturn(Optional.empty());
    }

    @Test
    public void whenInvalidIdthenClassroomShouldNotBeFound() {
        Classroom classroom = service.findClassroomById(0);
        assertThat(classroom).isNull();
        Util.verifyFindByIdIsCalledOnce(repository);
    }

    @Test
    public void whenValidIdthenClassroomShouldBeFound() {
        Classroom classroom = service.findClassroomById(1);
        assertThat(classroom.getId()).isEqualByComparingTo(1);
        Util.verifyFindByIdIsCalledOnce(repository);
    }
    
    @Test
    public void given3Classrooms_whengetAll_thenReturn3Records() {
        Classroom classroom1 = new Classroom(1, "Class of Phyics", 35);
        Classroom classroom2 = new Classroom(2, "Class of Engineering", 40);
        Classroom classroom3 = new Classroom(3, "Class of Phyics", 26);
        List<Classroom> classrooms = service.findAllClassrooms();
        Util.verifyFindAllIsCalledOnce(repository);
        assertThat(classrooms).hasSize(3).extracting(Classroom::getId).contains(classroom1.getId(), classroom2.getId(), classroom3.getId());
        assertThat(classrooms).hasSize(3).extracting(Classroom::getName).contains(classroom1.getName(), classroom2.getName(), classroom3.getName());
    }
    
}
