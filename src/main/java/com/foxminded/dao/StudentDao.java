package com.foxminded.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.domain.Group;
import com.foxminded.domain.Student;
import com.foxminded.mapper.GroupMapper;
import com.foxminded.mapper.StudentMapper;

@Component
public class StudentDao {

    private final String SQL_FIND_STUDENT = "select * from students where id = ?";
    private final String SQL_DELETE_STUDENT = "delete from students where id = ?";
    private final String SQL_UPDATE_STUDENT = "update students set first_name = ?, last_name = ?, age  = ? where id = ?";
    private final String SQL_GET_ALL_STUDENTS = "select * from students";
    private final String SQL_INSERT_STUDENT = "insert into students(id, first_name, last_name, age, group_id) values(?,?,?,?,?)";
    private final String SQL_GET_STUDENT_GROUP = "select * from groups inner join students "
            + "on groups.id = students.group_id where students.id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Student findStudentById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_STUDENT, new Object[] { id }, new StudentMapper());
    }

    public List<Student> findAllStudents() {
        return jdbcTemplate.query(SQL_GET_ALL_STUDENTS, new StudentMapper());
    }

    public boolean deleteStudent(Student student) {
        return jdbcTemplate.update(SQL_DELETE_STUDENT, student.getId()) > 0;
    }

    public boolean updateStudent(Student student) {
        return jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getFirstName(), student.getLastName(), student.getAge(),
                student.getId()) > 0;
    }

    public boolean createStudent(Student student) {
        return jdbcTemplate.update(SQL_INSERT_STUDENT, student.getId(), student.getFirstName(), student.getLastName(),
                student.getAge()) > 0;
    }

    public Group findStudentGroup(Student student) {
        student.setGroup(jdbcTemplate.queryForObject(SQL_GET_STUDENT_GROUP, new GroupMapper(), student.getId()));
        return student.getGroup();
    }

}
