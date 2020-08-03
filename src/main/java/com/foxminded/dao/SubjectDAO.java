package com.foxminded.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.domain.Subject;
import com.foxminded.domain.Teacher;
import com.foxminded.mapper.SubjectMapper;
import com.foxminded.mapper.TeacherMapper;

@Component
public class SubjectDAO {

    private final String SQL_FIND_SUBJECT = "select * from subjects where id = ?";
    private final String SQL_DELETE_SUBJECT = "delete from subjects where id = ?";
    private final String SQL_UPDATE_SUBJECT = "update teachers set name = ? where id = ?";
    private final String SQL_GET_ALL_SUBJECTS = "select * from subjects";
    private final String SQL_INSERT_SUBJECT = "insert into subjects(id, name) values(?,?)";
    private final String SQL_GET_SUBJECT_TEACHERS = "select * from teachers inner join teachers_subjects "
            + "on teachers.id = teachers_subjects.teacher_id where teachers_subjects.subject_id = ?";
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SubjectDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Subject findSubjectById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_SUBJECT, new Object[] { id }, new SubjectMapper());
    }

    public List<Subject> findAllSubjects() {
        return jdbcTemplate.query(SQL_GET_ALL_SUBJECTS, new SubjectMapper());
    }

    public boolean deleteSubject(Subject subject) {
        return jdbcTemplate.update(SQL_DELETE_SUBJECT, subject.getId()) > 0;
    }

    public boolean updateSubject(Subject subject) {
        return jdbcTemplate.update(SQL_UPDATE_SUBJECT, subject.getId(), subject.getName()) > 0;
    }

    public boolean createSubject(Subject subject) {
        return jdbcTemplate.update(SQL_INSERT_SUBJECT, subject.getId(), subject.getName()) > 0;
    }
    
    public List<Teacher> findSubjectTeachers(Subject subject){
        return jdbcTemplate.query(SQL_GET_SUBJECT_TEACHERS, new TeacherMapper(), subject.getId());
    }
}
