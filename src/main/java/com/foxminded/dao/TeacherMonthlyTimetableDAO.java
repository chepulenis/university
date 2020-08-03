package com.foxminded.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.domain.Lesson;
import com.foxminded.domain.TeacherMonthlyTimetable;
import com.foxminded.mapper.LessonMapper;
import com.foxminded.mapper.TeacherMonthlyTimetableMapper;

@Component
public class TeacherMonthlyTimetableDAO {
    private final String SQL_FIND_TEACHER_DAILY_TIMETABLE = "select * from teacher_monthly_timetable where id = ?";
    private final String SQL_FIND_MONTHLY_LESSONS_FOR_TEACHER = "select lessons.* from lessons where lessons.teacher_id = ? and "
            + "extract (year from lessons.start_time) = ? and extract (month from lessons.start_time) = ? order by lessons.start_time";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherMonthlyTimetableDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public TeacherMonthlyTimetable findTeacherTimetableById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_TEACHER_DAILY_TIMETABLE, new Object[] { id },
                new TeacherMonthlyTimetableMapper());
    }

    public List<Lesson> findMonthlyLessons(TeacherMonthlyTimetable teacherMonthlyTimetable, int teacherId, int year,
            int month) {
        teacherMonthlyTimetable.setLessons(
                jdbcTemplate.query(SQL_FIND_MONTHLY_LESSONS_FOR_TEACHER, new LessonMapper(), teacherId, year, month));
        return teacherMonthlyTimetable.getLessons();
    }
}
