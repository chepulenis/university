package com.foxminded.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.domain.TeacherDailyTimetable;

public class TeacherDailyTimetableMapper implements RowMapper<TeacherDailyTimetable>{
    
    public TeacherDailyTimetable mapRow(ResultSet resultSet, int i) throws SQLException{
        TeacherDailyTimetable teacherDailyTimetable = new TeacherDailyTimetable();
        teacherDailyTimetable.setId(resultSet.getInt("id"));
        teacherDailyTimetable.setDate(resultSet.getDate("date"));
        return teacherDailyTimetable;
    }
}
