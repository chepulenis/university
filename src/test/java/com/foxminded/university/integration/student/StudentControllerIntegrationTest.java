package com.foxminded.university.integration.student;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.foxminded.university.controller.StudentController;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
public class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService service;


    @Test
    public void whenPostStudentThenCreateStudent() throws Exception {
        Student student = new Student (1, "John", "Doe", 23, new Group(1, "zz-55"));
        given(service.createStudent(Mockito.any())).willReturn(student);

        mvc.perform(post("/students").contentType(MediaType.TEXT_HTML_VALUE)).andExpect(status().isFound())
                .andExpect(redirectedUrl("/students"));
        verify(service, VerificationModeFactory.times(1)).createStudent(Mockito.any());
        reset(service);
    }
}
