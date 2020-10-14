package com.foxminded.university.integration.subject;

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

import com.foxminded.university.controller.SubjectController;
import com.foxminded.university.domain.Subject;
import com.foxminded.university.service.SubjectService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SubjectController.class)
public class SubjectControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SubjectService service;

    @Test
    public void whenPostSubjectThenCreateSubject() throws Exception {
        Subject subject = new Subject(1, "Mathematics");
        given(service.createSubject(Mockito.any())).willReturn(subject);

        mvc.perform(post("/subjects").contentType(MediaType.TEXT_HTML_VALUE)).andExpect(status().isFound())
                .andExpect(redirectedUrl("/subjects"));
        verify(service, VerificationModeFactory.times(1)).createSubject(Mockito.any());
        reset(service);
    }
}
