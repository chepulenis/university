package com.foxminded.university.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.university.domain.Classroom;

public class ClassroomMapper implements RowMapper<Classroom>{
    
    public Classroom mapRow(ResultSet resultSet, int i) throws SQLException {
        Classroom classroom = new Classroom();
        classroom.setId(resultSet.getInt("id"));
        classroom.setName(resultSet.getString("name"));
        classroom.setSize(resultSet.getInt("size"));
        return classroom;
    } 
    
}
