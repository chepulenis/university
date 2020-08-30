package com.foxminded.service;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.TeacherDailyTimetableDao;
import com.foxminded.domain.Lesson;
import com.foxminded.domain.TeacherDailyTimetable;

@Service
public class TeacherDailyTimetableService {

    @Autowired
    private TeacherDailyTimetableDao dao;
    private static final Logger logger = LoggerFactory.getLogger(TeacherDailyTimetableDao.class);
    
    public TeacherDailyTimetable findTeacherTimetableById(int id) {
        TeacherDailyTimetable teacherDailyTimetable = dao.findTeacherTimetableById(id);
        logger.info("Teacher daily {} timetable by id {} founded", teacherDailyTimetable, id);
        return teacherDailyTimetable;
    }
    
    public List<Lesson> findDailyLessons(TeacherDailyTimetable teacherDailyTimetable, int teacherId, Date date) {
        List<Lesson> lessons = dao.findDailyLessons(teacherDailyTimetable, teacherId, date);
        logger.info("Daily lessons {} for teacher by id {} by date {} founded", lessons, teacherId, date);
        return lessons;
    }
    
}
