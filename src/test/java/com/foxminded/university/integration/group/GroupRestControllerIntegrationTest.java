package com.foxminded.university.integration.group;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.foxminded.university.domain.Group;
import com.foxminded.university.integration.Util;
import com.foxminded.university.repository.GroupRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GroupRestControllerIntegrationTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    GroupRepository repository;
    
    @After
    public void resetDb() {
        repository.deleteAll();
    }
    
    @Test
    public void whenValidInputThenCreateGroup() throws IOException, Exception {
        Group group = new Group(1, "zz-22");
        mvc.perform(post("/groups").contentType(MediaType.APPLICATION_JSON).content(Util.toJson(group)));
       
        List<Group> found = repository.findAll();
        assertThat(found).extracting(Group::getId).containsOnly(1);
    }
    
    @Test
    public void givenGroupsWhenGetGroupsThenStatus200() throws Exception{
        Group group1 = new Group(1, "af-33");
        repository.saveAndFlush(group1);
        Group group2 = new Group(2, "bx-73");
        repository.saveAndFlush(group2);
        
        mvc.perform(get("/groups").contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
        .andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("af-33")))
        .andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].name", is("bx-73")));
    }
    
}
