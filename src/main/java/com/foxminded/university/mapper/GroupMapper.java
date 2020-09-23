package com.foxminded.university.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.university.domain.Group;

public class GroupMapper implements RowMapper<Group>{
    
    public Group mapRow(ResultSet resultSet, int i) throws SQLException{
        Group group = new Group();
        group.setId(resultSet.getInt("id"));
        group.setName(resultSet.getString("name"));
        return group;
    }

}
