package com.foxminded.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.domain.GroupMonthlyTimetable;

public class GroupMonthlyTimetableMapper implements RowMapper<GroupMonthlyTimetable>{
    public GroupMonthlyTimetable mapRow(ResultSet resultSet, int i) throws SQLException{
        GroupMonthlyTimetable groupMonthlyTimetable = new GroupMonthlyTimetable();
        groupMonthlyTimetable.setId(resultSet.getInt("id"));
        return groupMonthlyTimetable;
    }

}
