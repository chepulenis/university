package com.foxminded.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.GroupDailyTimetableDao;
import com.foxminded.domain.GroupDailyTimetable;
import com.foxminded.domain.Lesson;

@Service
public class GroupDailyTimetableService {

    @Autowired
    private GroupDailyTimetableDao dao;

    public GroupDailyTimetable findGroupTimetableById (int id) {
        return dao.findGroupTimetableById(id);
    }
    
    public List<Lesson> findDailyLessons(GroupDailyTimetable groupDailyTimetable, int groupId, Date date) {
        return dao.findDailyLessons(groupDailyTimetable, groupId, date);
    }
    
}
