package com.foxminded.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.domain.GroupMonthlyTimetable;
import com.foxminded.domain.Lesson;
import com.foxminded.mapper.GroupMonthlyTimetableMapper;
import com.foxminded.mapper.LessonMapper;

@Component
public class GroupMonthlyTimetableDao {
    
    private final String SQL_FIND_GROUP_DAILY_TIMETABLE = "select * from group_monthly_timetable where id = ?";
    private final String SQL_FIND_MONTHLY_LESSONS_FOR_GROUP = "select * from lessons where lessons.group_id = ? and "
            + "extract (year from lessons.start_time) = ? and extract (month from lessons.start_time) = ? order by lessons.start_time";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupMonthlyTimetableDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public GroupMonthlyTimetable findGroupTimetableById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_GROUP_DAILY_TIMETABLE, new Object[] { id },
                new GroupMonthlyTimetableMapper());
    }

    public List<Lesson> findMonthlyLessons(GroupMonthlyTimetable groupMonthlyTimetable, int groupId, int year,
            int month) {
        groupMonthlyTimetable.setLessons(
                jdbcTemplate.query(SQL_FIND_MONTHLY_LESSONS_FOR_GROUP, new LessonMapper(), groupId, year, month));
        return groupMonthlyTimetable.getLessons();
    }
}
