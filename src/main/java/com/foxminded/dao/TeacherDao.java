package com.foxminded.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.domain.Subject;
import com.foxminded.domain.Teacher;
import com.foxminded.mapper.SubjectMapper;
import com.foxminded.mapper.TeacherMapper;

@Component
public class TeacherDao {

    private final String SQL_FIND_TEACHER = "select * from teachers where id = ?";
    private final String SQL_DELETE_TEACHER = "delete from teachers where id = ?";
    private final String SQL_UPDATE_TEACHER = "update teachers set first_name = ?, last_name = ?, age  = ? where id = ?";
    private final String SQL_GET_ALL_TEACHERS = "select * from teachers";
    private final String SQL_INSERT_TEACHER = "insert into teachers(id, first_name, last_name, age) values(?,?,?,?)";
    private final String SQL_GET_TEACHER_SUBJECTS = "select * from subjects inner join teachers_subjects "
            + "on subjects.id = teachers_subjects.subject_id where teachers_subjects.teacher_id = ?";
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Teacher findTeacherById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_TEACHER, new Object[] { id }, new TeacherMapper());
    }

    public List<Teacher> findAllTeachers() {
        return jdbcTemplate.query(SQL_GET_ALL_TEACHERS, new TeacherMapper());
    }

    public boolean deleteTeacher(Teacher teacher) {
        return jdbcTemplate.update(SQL_DELETE_TEACHER, teacher.getId()) > 0;
    }

    public boolean updateTeacher(Teacher teacher) {
        return jdbcTemplate.update(SQL_UPDATE_TEACHER, teacher.getFirstName(), teacher.getLastName(), teacher.getAge(),
                teacher.getId()) > 0;
    }

    public boolean createTeacher(Teacher teacher) {
        return jdbcTemplate.update(SQL_INSERT_TEACHER, teacher.getId(), teacher.getFirstName(), teacher.getLastName(),
                teacher.getAge()) > 0;
    }

    public List<Subject> findTeacherSubjects(Teacher teacher) {
        return jdbcTemplate.query(SQL_GET_TEACHER_SUBJECTS, new SubjectMapper(), teacher.getId());
    }

}
