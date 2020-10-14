package com.foxminded.university.integration.teacher;

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

import com.foxminded.university.controller.TeacherController;
import com.foxminded.university.domain.Teacher;
import com.foxminded.university.service.TeacherService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TeacherController.class)
public class TeacherControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeacherService service;


    @Test
    public void whenPostTeacherThenCreateTeacher() throws Exception {
        Teacher teacher = new Teacher (1, "Andrew", "Robertson", 38);
        given(service.createTeacher(Mockito.any())).willReturn(teacher);

        mvc.perform(post("/teachers").contentType(MediaType.TEXT_HTML_VALUE)).andExpect(status().isFound())
                .andExpect(redirectedUrl("/teachers"));
        verify(service, VerificationModeFactory.times(1)).createTeacher(Mockito.any());
        reset(service);
    }
}
