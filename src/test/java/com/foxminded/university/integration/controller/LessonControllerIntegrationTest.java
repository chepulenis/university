package com.foxminded.university.integration.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

import com.foxminded.university.controller.LessonController;
import com.foxminded.university.domain.Classroom;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lesson;
import com.foxminded.university.domain.Subject;
import com.foxminded.university.domain.Teacher;
import com.foxminded.university.service.LessonService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LessonController.class)
public class LessonControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LessonService service;

    @Test
    public void whenPostLessonThenCreateLesson() throws Exception {
        Lesson lesson = new Lesson(1, new Classroom(1, "Class of Mathematics", 35), new Teacher(1, "Sam", "Clark", 33),
                new Subject(1, "Math"), new Group(1, "az-22"),
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 8, 0).getTime());
        given(service.createLesson(Mockito.any())).willReturn(lesson);

        mvc.perform(post("/lessons").contentType(MediaType.TEXT_HTML_VALUE)).andExpect(status().isFound())
                .andExpect(redirectedUrl("/lessons"));
        verify(service, VerificationModeFactory.times(1)).createLesson(Mockito.any());
        reset(service);
    }
}
