package com.foxminded.university.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.university.domain.Teacher;

public class TeacherMapper implements RowMapper<Teacher> {
    
    public Teacher mapRow(ResultSet resultSet, int i) throws SQLException{
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getInt("id"));
        teacher.setFirstName(resultSet.getString("first_name"));
        teacher.setLastName(resultSet.getString("last_name"));
        teacher.setAge(resultSet.getInt("age"));
        return teacher;
    }

}
