package com.foxminded.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.domain.Lesson;

public class LessonMapper implements RowMapper<Lesson> {

    public Lesson mapRow(ResultSet resultSet, int i) throws SQLException{
        Lesson lesson = new Lesson ();
        lesson.setId(resultSet.getInt("id"));
        lesson.setTimestamp(resultSet.getTimestamp("start_time"));
        return lesson;
    }

}
