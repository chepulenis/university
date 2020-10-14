package com.foxminded.university.integration.lesson;

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
import java.util.Calendar;
import java.util.GregorianCalendar;
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

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lesson;
import com.foxminded.university.domain.Subject;
import com.foxminded.university.domain.Teacher;
import com.foxminded.university.integration.Util;
import com.foxminded.university.repository.ClassroomRepository;
import com.foxminded.university.repository.GroupRepository;
import com.foxminded.university.repository.LessonRepository;
import com.foxminded.university.repository.SubjectRepository;
import com.foxminded.university.repository.TeacherRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LessonRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    GroupRepository groupRepository;

    @After
    public void resetDb() {
        lessonRepository.deleteAll();
        classroomRepository.deleteAll();
        teacherRepository.deleteAll();
        subjectRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    public void whenValidInputThenCreateLesson() throws IOException, Exception {
        Classroom classroom = new Classroom(1, "Class of Mathematics", 35);
        Teacher teacher = new Teacher(1, "Sam", "Clark", 33);
        Subject subject = new Subject(1, "Math");
        Group group = new Group(1, "az-22");
        Lesson lesson = new Lesson(1, classroom, teacher, subject, group,
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 8, 0).getTime());
        classroomRepository.saveAndFlush(classroom);
        teacherRepository.saveAndFlush(teacher);
        subjectRepository.saveAndFlush(subject);
        groupRepository.saveAndFlush(group);
        lessonRepository.saveAndFlush(lesson);

        mvc.perform(post("/lessons").contentType(MediaType.APPLICATION_JSON).content(Util.toJson(classroom)));

        List<Lesson> found = lessonRepository.findAll();
        assertThat(found).extracting(Lesson::getId).containsOnly(1);
    }

    @Test
    public void givenLessonsWhenGetLessonsThenStatus200() throws Exception {
        Classroom classroom1 = new Classroom(1, "Class of Mathematics", 35);
        Teacher teacher1 = new Teacher(1, "Sam", "Clark", 33);
        Subject subject1 = new Subject(1, "Math");
        Group group1 = new Group(1, "az-22");
        Lesson lesson1 = new Lesson(1, classroom1, teacher1, subject1, group1,
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 8, 0).getTime());
        classroomRepository.saveAndFlush(classroom1);
        teacherRepository.saveAndFlush(teacher1);
        subjectRepository.saveAndFlush(subject1);
        groupRepository.saveAndFlush(group1);
        lessonRepository.saveAndFlush(lesson1);

        Classroom classroom2 = new Classroom(2, "Class of Arts", 24);
        Teacher teacher2 = new Teacher(2, "Jack", "Lee", 33);
        Subject subject2 = new Subject(2, "Arts");
        Group group2 = new Group(2, "ff-34");
        Lesson lesson2 = new Lesson(2, classroom2, teacher2, subject2, group2,
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 4, 9, 0).getTime());

        classroomRepository.saveAndFlush(classroom2);
        teacherRepository.saveAndFlush(teacher2);
        subjectRepository.saveAndFlush(subject2);
        groupRepository.saveAndFlush(group2);
        lessonRepository.saveAndFlush(lesson2);

        mvc.perform(get("/lessons").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2)))).andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].classroom.name", is("Class of Mathematics")))
                .andExpect(jsonPath("$[0].teacher.firstName", is("Sam")))
                .andExpect(jsonPath("$[0].teacher.lastName", is("Clark")))
                .andExpect(jsonPath("$[0].subject.name", is("Math")))
                .andExpect(jsonPath("$[0].group.name", is("az-22"))).andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].classroom.name", is("Class of Arts")))
                .andExpect(jsonPath("$[1].teacher.firstName", is("Jack")))
                .andExpect(jsonPath("$[1].teacher.lastName", is("Lee")))
                .andExpect(jsonPath("$[1].subject.name", is("Arts")))
                .andExpect(jsonPath("$[1].group.name", is("ff-34")));
    }

}
