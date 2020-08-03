package com.foxminded.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.domain.Classroom;
import com.foxminded.domain.Group;
import com.foxminded.domain.Lesson;
import com.foxminded.domain.Subject;
import com.foxminded.domain.Teacher;
import com.foxminded.mapper.ClassroomMapper;
import com.foxminded.mapper.GroupMapper;
import com.foxminded.mapper.LessonMapper;
import com.foxminded.mapper.SubjectMapper;
import com.foxminded.mapper.TeacherMapper;

@Component
public class LessonDAO {

    private final String SQL_FIND_LESSON = "select * from lessons where id = ?";
    private final String SQL_FIND_LESSON_CLASSROOM =  "select * from classrooms inner join lessons "
            + "on classrooms.id = lessons.classroom_id where lessons.id = ?";
    private final String SQL_FIND_LESSON_TEACHER = "select * from teachers inner join lessons "
            + "on teachers.id = lessons.teacher_id where lessons.id = ?";
    private final String SQL_FIND_LESSON_SUBJECT = "select * from subjects inner join lessons "
            + "on subjects.id = lessons.subject_id where lessons.id = ?";
    private final String SQL_FIND_LESSON_GROUP = "select * from groups inner join lessons "
            + "on groups.id = lessons.group_id where lessons.id = ?";
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LessonDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Lesson findLessonById (int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_LESSON, new Object[] { id }, new LessonMapper());
    }
    
    public Classroom findClassroom(Lesson lesson) {
        lesson.setClassroom(jdbcTemplate.queryForObject(SQL_FIND_LESSON_CLASSROOM, new ClassroomMapper(), lesson.getId()));
        return lesson.getClassroom();
    }
    
    public Teacher findTeacher(Lesson lesson) {
        lesson.setTeacher(jdbcTemplate.queryForObject(SQL_FIND_LESSON_TEACHER, new TeacherMapper(), lesson.getId()));
        return lesson.getTeacher();
    }
    
    public Subject findSubject(Lesson lesson) {
        lesson.setSubject(jdbcTemplate.queryForObject(SQL_FIND_LESSON_SUBJECT, new SubjectMapper(), lesson.getId()));
        return lesson.getSubject();
    }
    
    public Group findGroup(Lesson lesson) {
        lesson.setGroup(jdbcTemplate.queryForObject(SQL_FIND_LESSON_GROUP, new GroupMapper(), lesson.getId()));
        return lesson.getGroup();
    } 
}
