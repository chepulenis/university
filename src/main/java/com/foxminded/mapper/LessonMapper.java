package com.foxminded.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.domain.Classroom;
import com.foxminded.domain.Group;
import com.foxminded.domain.Lesson;
import com.foxminded.domain.Subject;
import com.foxminded.domain.Teacher;

public class LessonMapper implements RowMapper<Lesson> {

    public Lesson mapRow(ResultSet resultSet, int i) throws SQLException{
        Lesson lesson = new Lesson ();
        lesson.setId(resultSet.getInt("lesson_id"));
        lesson.setClassroom(new Classroom(resultSet.getInt("classroom_id"), resultSet.getString("classroom_name"), resultSet.getInt("classroom_size")));
        lesson.setTeacher(new Teacher(resultSet.getInt("teacher_id"), resultSet.getString("teacher_first_name"), resultSet.getString("teacher_last_name"), resultSet.getInt("teacher_age")));
        lesson.setSubject(new Subject(resultSet.getInt("subject_id"), resultSet.getString("subject_name")));
        lesson.setGroup(new Group(resultSet.getInt("group_id"), resultSet.getString("group_name")));
        lesson.setStartTime(resultSet.getTimestamp("start_time"));
        return lesson;
    }

}
