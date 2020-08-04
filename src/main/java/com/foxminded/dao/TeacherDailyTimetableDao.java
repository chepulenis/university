package com.foxminded.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.domain.Lesson;
import com.foxminded.domain.TeacherDailyTimetable;
import com.foxminded.mapper.LessonMapper;
import com.foxminded.mapper.TeacherDailyTimetableMapper;

@Component
public class TeacherDailyTimetableDao {

    private final String SQL_FIND_TEACHER_DAILY_TIMETABLE = "select * from teacher_daily_timetable where id = ?";
    private final String SQL_FIND_DAILY_LESSONS_FOR_TEACHER = "select lessons.*, teacher_daily_timetable.date as date from lessons, teacher_daily_timetable where "
            + "lessons.teacher_id = ? and date = ? and lessons.start_time between date and date + interval '23:59' hour";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherDailyTimetableDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public TeacherDailyTimetable findTeacherTimetableById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_TEACHER_DAILY_TIMETABLE, new Object[] { id }, new TeacherDailyTimetableMapper());
    }

    public List<Lesson> findDailyLessons(TeacherDailyTimetable teacherDailyTimetable, int teacherId, Date date) {
        teacherDailyTimetable.setLessons(jdbcTemplate.query(SQL_FIND_DAILY_LESSONS_FOR_TEACHER, new LessonMapper(), teacherId, date));
        return teacherDailyTimetable.getLessons();
    }
}
