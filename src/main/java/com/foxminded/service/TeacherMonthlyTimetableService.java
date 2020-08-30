package com.foxminded.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.TeacherMonthlyTimetableDao;
import com.foxminded.domain.Lesson;
import com.foxminded.domain.TeacherMonthlyTimetable;
import com.foxminded.exception.TimetableNotDefinedException;

@Service
public class TeacherMonthlyTimetableService {
    
    private final static int JUNE = 6;
    private final static int AUGUST = 8;
    
    @Autowired
    private TeacherMonthlyTimetableDao dao;
    private static final Logger logger = LoggerFactory.getLogger(TeacherMonthlyTimetableService.class);
    
    public TeacherMonthlyTimetable findTeacherTimetableById(int id) {
        TeacherMonthlyTimetable teacherMonthlyTimetable = dao.findTeacherTimetableById(id);
        logger.info("Teacher monthly timetable {} by id {} founded", teacherMonthlyTimetable, id);
        return dao.findTeacherTimetableById(id);
    }

    public List<Lesson> findMonthlyLessons(TeacherMonthlyTimetable teacherMonthlyTimetable, int teacherId, int year,
            int month) throws TimetableNotDefinedException {
        if (month >= JUNE && month <= AUGUST) {
            logger.error("It's summer holidays. Timetable is not avialable.");
            throw new TimetableNotDefinedException("It's summer holidays. Timetable is not avialable.");
        }
        List<Lesson> lessons = dao.findMonthlyLessons(teacherMonthlyTimetable, teacherId, year, month);
        logger.info("Monthly lessons {} for teacher by id {} by year {}, month {}", lessons, teacherId, year, month);
        return lessons;
    }
}
