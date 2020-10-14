package com.foxminded.university.integration.group;

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

import com.foxminded.university.controller.GroupController;
import com.foxminded.university.domain.Group;
import com.foxminded.university.service.GroupService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GroupController.class)
public class GroupControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GroupService service;


    @Test
    public void whenPostGroupThenCreateGroup() throws Exception {
        Group group = new Group(1, "zz-22");
        given(service.createGroup(Mockito.any())).willReturn(group);

        mvc.perform(post("/groups").contentType(MediaType.TEXT_HTML_VALUE)).andExpect(status().isFound())
                .andExpect(redirectedUrl("/groups"));
        verify(service, VerificationModeFactory.times(1)).createGroup(Mockito.any());
        reset(service);
    }
}
