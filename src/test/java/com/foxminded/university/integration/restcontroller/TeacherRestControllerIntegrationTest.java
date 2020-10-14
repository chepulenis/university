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

import com.foxminded.university.domain.Teacher;
import com.foxminded.university.integration.Util;
import com.foxminded.university.repository.TeacherRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TeacherRestControllerIntegrationTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    TeacherRepository repository;
    
    @After
    public void resetDb() {
        repository.deleteAll();
    }
    
    @Test
    public void whenValidInputThenCreateTeacher() throws IOException, Exception {
        Teacher teacher = new Teacher(1, "Edward", "Taylor", 52);
        mvc.perform(post("/teachers").contentType(MediaType.APPLICATION_JSON).content(Util.toJson(teacher)));
       
        List<Teacher> found = repository.findAll();
        assertThat(found).extracting(Teacher::getId).containsOnly(1);
    }
    
    @Test
    public void givenTeachersWhenGetTeachersThenStatus200() throws Exception{
        Teacher teacher1 = new Teacher(1, "Edward", "Taylor", 52);
        repository.saveAndFlush(teacher1);
        Teacher teacher2 = new Teacher(2, "William", "Clark", 45);
        repository.saveAndFlush(teacher2);
        
        mvc.perform(get("/teachers").contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
        .andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].firstName", is("Edward"))).andExpect(jsonPath("$[0].lastName", is("Taylor"))).andExpect(jsonPath("$[0].age", is(52)))
        .andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].firstName", is("William"))).andExpect(jsonPath("$[1].lastName", is("Clark"))).andExpect(jsonPath("$[1].age", is(45)));
    }
    
}
