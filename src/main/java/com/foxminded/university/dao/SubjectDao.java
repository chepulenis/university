package com.foxminded.university.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.university.domain.Subject;
import com.foxminded.university.domain.Teacher;
import com.foxminded.university.mapper.SubjectMapper;
import com.foxminded.university.mapper.TeacherMapper;

@Component
public class SubjectDao {

    private static final String SQL_FIND_SUBJECT = "select * from subjects where id = ?";
    private static final String SQL_DELETE_SUBJECT = "delete from subjects where id = ?";
    private static final String SQL_UPDATE_SUBJECT = "update subjects set name = ? where id = ?";
    private static final String SQL_GET_ALL_SUBJECTS = "select * from subjects order by id";
    private static final String SQL_INSERT_SUBJECT = "insert into subjects(id, name) values(?,?)";
    private static final String SQL_GET_SUBJECT_TEACHERS = "select * from teachers inner join teachers_subjects "
            + "on teachers.id = teachers_subjects.teacher_id where teachers_subjects.subject_id = ?";
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SubjectDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Subject findSubjectById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_SUBJECT, new Object[] { id }, new SubjectMapper());
    }

    public List<Subject> findAllSubjects() {
        return jdbcTemplate.query(SQL_GET_ALL_SUBJECTS, new SubjectMapper());
    }

    public boolean deleteSubject(int id) {
        return jdbcTemplate.update(SQL_DELETE_SUBJECT, id) > 0;
    }

    public boolean updateSubject(Subject subject) {
        return jdbcTemplate.update(SQL_UPDATE_SUBJECT, subject.getName(), subject.getId()) > 0;
    }

    public boolean createSubject(Subject subject) {
        return jdbcTemplate.update(SQL_INSERT_SUBJECT, subject.getId(), subject.getName()) > 0;
    }
    
    public List<Teacher> findSubjectTeachers(Subject subject){
        return jdbcTemplate.query(SQL_GET_SUBJECT_TEACHERS, new TeacherMapper(), subject.getId());
    }
}
