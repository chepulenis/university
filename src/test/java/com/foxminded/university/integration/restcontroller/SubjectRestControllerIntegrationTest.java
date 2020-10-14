package com.foxminded.university.integration.restcontroller;

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

import com.foxminded.university.domain.Subject;
import com.foxminded.university.integration.Util;
import com.foxminded.university.repository.SubjectRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SubjectRestControllerIntegrationTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    SubjectRepository repository;
    
    @After
    public void resetDb() {
        repository.deleteAll();
    }
    
    @Test
    public void whenValidInputThenCreateSubject() throws IOException, Exception {
        Subject subject = new Subject(1, "Math");
        mvc.perform(post("/subjects").contentType(MediaType.APPLICATION_JSON).content(Util.toJson(subject)));
       
        List<Subject> found = repository.findAll();
        assertThat(found).extracting(Subject::getId).containsOnly(1);
    }
    
    @Test
    public void givenSubjectsWhenGetSubjectsThenStatus200() throws Exception{
        Subject subject1 = new Subject(1, "Physics");
        repository.saveAndFlush(subject1);
        Subject subject2 = new Subject(2, "Chemistry");
        repository.saveAndFlush(subject2);
        
        mvc.perform(get("/subjects").contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
        .andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("Physics")))
        .andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].name", is("Chemistry")));
    }
    
}
