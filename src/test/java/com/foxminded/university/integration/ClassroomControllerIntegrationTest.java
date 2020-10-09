package com.foxminded.university.integration;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
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

import com.foxminded.university.controller.ClassroomController;
import com.foxminded.university.domain.Classroom;
import com.foxminded.university.service.ClassroomService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ClassroomController.class)
public class ClassroomControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClassroomService service;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void whenPostClassroomThenCreateClassroom() throws Exception {
        Classroom classroom = new Classroom(1, "Class of Mathematics", 28);
        given(service.createClassroom(Mockito.any())).willReturn(classroom);

        mvc.perform(post("/classrooms").contentType(MediaType.TEXT_HTML_VALUE)).andExpect(status().isFound())
                .andExpect(redirectedUrl("/classrooms"));
        verify(service, VerificationModeFactory.times(1)).createClassroom(Mockito.any());
        reset(service);
    }
}
