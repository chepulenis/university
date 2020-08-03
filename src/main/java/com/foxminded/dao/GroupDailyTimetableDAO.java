package com.foxminded.dao;

import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.domain.GroupDailyTimetable;
import com.foxminded.domain.Lesson;
import com.foxminded.mapper.GroupDailyTimetableMapper;
import com.foxminded.mapper.LessonMapper;

@Component
public class GroupDailyTimetableDAO {
    private final String SQL_FIND_GROUP_DAILY_TIMETABLE = "select * from group_daily_timetable where id = ?";
    private final String SQL_FIND_DAILY_LESSONS_FOR_GROUP = "select lessons.*, group_daily_timetable.date as date from lessons, group_daily_timetable where "
            + "lessons.group_id = ? and date = ? and lessons.start_time between date and date + interval '23:59' hour";
    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GroupDailyTimetableDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public GroupDailyTimetable findGroupTimetableById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_GROUP_DAILY_TIMETABLE, new Object[] { id }, new GroupDailyTimetableMapper());
    }

    public List<Lesson> findDailyLessons(GroupDailyTimetable groupDailyTimetable, int groupId, Date date) {
        groupDailyTimetable.setLessons(jdbcTemplate.query(SQL_FIND_DAILY_LESSONS_FOR_GROUP, new LessonMapper(), groupId, date));
        return groupDailyTimetable.getLessons();
    }
}
