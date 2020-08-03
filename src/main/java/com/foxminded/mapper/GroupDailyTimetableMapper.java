package com.foxminded.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.domain.GroupDailyTimetable;

public class GroupDailyTimetableMapper implements RowMapper<GroupDailyTimetable>{
    
    public GroupDailyTimetable mapRow(ResultSet resultSet, int i) throws SQLException{
        GroupDailyTimetable groupDailyTimetable = new GroupDailyTimetable();
        groupDailyTimetable.setId(resultSet.getInt("id"));
        groupDailyTimetable.setDate(resultSet.getDate("date"));
        return groupDailyTimetable;
    }

}
