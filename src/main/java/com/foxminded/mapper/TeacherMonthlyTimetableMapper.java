package com.foxminded.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.domain.TeacherMonthlyTimetable;

public class TeacherMonthlyTimetableMapper implements RowMapper<TeacherMonthlyTimetable>{
    
    public TeacherMonthlyTimetable mapRow(ResultSet resultSet, int i) throws SQLException{
        TeacherMonthlyTimetable teacherMonthlyTimetable = new TeacherMonthlyTimetable();
        teacherMonthlyTimetable.setId(resultSet.getInt("id"));
        return teacherMonthlyTimetable;
    }

}
