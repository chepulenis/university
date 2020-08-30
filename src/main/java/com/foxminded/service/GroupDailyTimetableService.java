package com.foxminded.service;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.GroupDailyTimetableDao;
import com.foxminded.domain.GroupDailyTimetable;
import com.foxminded.domain.Lesson;

@Service
public class GroupDailyTimetableService {

    @Autowired
    private GroupDailyTimetableDao dao;
    private static final Logger logger = LoggerFactory.getLogger(GroupDailyTimetableService.class);
    
    public GroupDailyTimetable findGroupTimetableById (int id) {
        GroupDailyTimetable groupDailyTimetable = dao.findGroupTimetableById(id);
        logger.info("Group daily {} timetable by id {} founded", groupDailyTimetable, id);
        return groupDailyTimetable;
    }
    
    public List<Lesson> findDailyLessons(GroupDailyTimetable groupDailyTimetable, int groupId, Date date) {
        List<Lesson> lessons = dao.findDailyLessons(groupDailyTimetable, groupId, date);
        logger.info("Daily lessons {} for group by id {} by date {} founded", lessons, groupId, date);
        return lessons;
    }
    
}
