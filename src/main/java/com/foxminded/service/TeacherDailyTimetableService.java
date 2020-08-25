package com.foxminded.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.TeacherDailyTimetableDao;
import com.foxminded.domain.Lesson;
import com.foxminded.domain.TeacherDailyTimetable;

@Service
public class TeacherDailyTimetableService {

    @Autowired
    private TeacherDailyTimetableDao dao;
    
    public TeacherDailyTimetable findTeacherTimetableById(int id) {
        return dao.findTeacherTimetableById(id);
    }
    
    public List<Lesson> findDailyLessons(TeacherDailyTimetable teacherDailyTimetable, int teacherId, Date date) {
        return dao.findDailyLessons(teacherDailyTimetable, teacherId, date);
    }
    
}
