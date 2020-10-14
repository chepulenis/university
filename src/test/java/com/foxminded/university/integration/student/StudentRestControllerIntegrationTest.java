package com.foxminded.university.integration.student;

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
import com.foxminded.university.domain.Student;
import com.foxminded.university.integration.Util;
import com.foxminded.university.repository.GroupRepository;
import com.foxminded.university.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StudentRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupRepository groupRepository;

    @After
    public void resetDb() {
        studentRepository.deleteAll();
    }

    @Test
    public void whenValidInputThenCreateStudent() throws IOException, Exception {
        Group group = new Group(1, "zz-55");
        Student student = new Student(1, "John", "Doe", 23, group);
        groupRepository.saveAndFlush(group);
        studentRepository.saveAndFlush(student);
        mvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(Util.toJson(student)));

        List<Student> found = studentRepository.findAll();
        assertThat(found).extracting(Student::getId).containsOnly(1);
    }

    @Test
    public void givenStudentsWhenGetStudentsThenStatus200() throws Exception {
        Group group1 = new Group(1, "zz-55");
        Student student1 = new Student(1, "John", "Doe", 23, group1);
        groupRepository.saveAndFlush(group1);
        studentRepository.saveAndFlush(student1);
        Group group2 = new Group(2, "ax-31");
        Student student2 = new Student(2, "Jane", "Wilson", 19, group2);
        groupRepository.saveAndFlush(group2);
        studentRepository.saveAndFlush(student2);

        mvc.perform(get("/students").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2)))).andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("John"))).andExpect(jsonPath("$[0].lastName", is("Doe")))
                .andExpect(jsonPath("$[0].age", is(23))).andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Jane"))).andExpect(jsonPath("$[1].lastName", is("Wilson")))
                .andExpect(jsonPath("$[1].age", is(19)));
    }

}
