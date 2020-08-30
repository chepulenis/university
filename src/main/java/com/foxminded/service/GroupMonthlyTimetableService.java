package com.foxminded.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.GroupMonthlyTimetableDao;
import com.foxminded.domain.GroupMonthlyTimetable;
import com.foxminded.domain.Lesson;
import com.foxminded.exception.TimetableNotDefinedException;

@Service
public class GroupMonthlyTimetableService {
    
    private final static int JUNE = 6;
    private final static int AUGUST = 8;
    
    @Autowired
    private GroupMonthlyTimetableDao dao;
    private static final Logger logger = LoggerFactory.getLogger(GroupMonthlyTimetableService.class);
    
    public GroupMonthlyTimetable findGroupTimetableById(int id) {
        GroupMonthlyTimetable groupMonthlyTimetable = dao.findGroupTimetableById(id);
        logger.info("Group monthly timetable {} by id {} founded", groupMonthlyTimetable, id);
        return groupMonthlyTimetable;
    }
    
    public List<Lesson> findMonthlyLessons(GroupMonthlyTimetable groupMonthlyTimetable, int groupId, int year,
            int month) throws TimetableNotDefinedException {
        if (month >= JUNE && month <= AUGUST) {
            logger.error("It's summer holidays. Timetable is not avialable.");
            throw new TimetableNotDefinedException("It's summer holidays. Timetable is not avialable.");
        }
        List<Lesson> lessons = dao.findMonthlyLessons(groupMonthlyTimetable, groupId, year, month);
        logger.info("Monthly lessons {} for group by id {} by year {}, month {}", lessons, groupId, year, month);
        return lessons;
    }

}
