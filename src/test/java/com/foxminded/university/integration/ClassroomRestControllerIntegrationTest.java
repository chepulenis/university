package com.foxminded.university.integration;

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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.repository.ClassroomRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@EnableAutoConfiguration
//@PropertySource("classpath:/university/src/test/resources/application.properties")
@TestPropertySource(locations = "classpath:application-test.properties")

public class ClassroomRestControllerIntegrationTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    ClassroomRepository repository;
    
    @After
    public void resetDb() {
        repository.deleteAll();
    }
    
    @Test
    public void whenValidInputThenCreateClassroom() throws IOException, Exception {
        Classroom classroom = new Classroom(1, "Class of zzz", 28);
        mvc.perform(post("/classrooms").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(classroom)));
       
        List<Classroom> found = repository.findAll();
        assertThat(found).extracting(Classroom::getId).containsOnly(1);
    }
    
    @Test
    public void givenClassroomsWhenGetClassroomsThenStatus200() throws Exception{
        Classroom classroom1 = new Classroom(1, "Class of Arts", 24);
        repository.saveAndFlush(classroom1);
        Classroom classroom2 = new Classroom(2, "Class of Physics", 30);
        repository.saveAndFlush(classroom2);
        
        mvc.perform(get("/classrooms").contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
        .andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("Class of Arts")))
        .andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].name", is("Class of Physics")));
    }
    
}
