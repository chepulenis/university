package com.foxminded.university.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public class StudentMapper implements RowMapper<Student> {
    
    public Student mapRow(ResultSet resultSet, int i) throws SQLException{
        Student student = new Student();
        student.setId(resultSet.getInt("student_id"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setAge(resultSet.getInt("age"));
        student.setGroup(new Group(resultSet.getInt("group_id"), resultSet.getString("name")));
        return student;
    }
}
