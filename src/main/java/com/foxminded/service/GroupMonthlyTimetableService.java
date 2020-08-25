package com.foxminded.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.GroupMonthlyTimetableDao;
import com.foxminded.domain.GroupMonthlyTimetable;
import com.foxminded.domain.Lesson;

@Service
public class GroupMonthlyTimetableService {
    
    @Autowired
    private GroupMonthlyTimetableDao dao;
    
    public GroupMonthlyTimetable findGroupTimetableById(int id) {
        return dao.findGroupTimetableById(id);
    }
    
    public List<Lesson> findMonthlyLessons(GroupMonthlyTimetable groupMonthlyTimetable, int groupId, int year,
            int month) {
        return dao.findMonthlyLessons(groupMonthlyTimetable, groupId, year, month);
    }

}
