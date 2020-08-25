package com.foxminded.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.TeacherMonthlyTimetableDao;
import com.foxminded.domain.Lesson;
import com.foxminded.domain.TeacherMonthlyTimetable;

@Service
public class TeacherMonthlyTimetableService {
    
    @Autowired
    private TeacherMonthlyTimetableDao dao;
    
    public TeacherMonthlyTimetable findTeacherTimetableById(int id) {
        return dao.findTeacherTimetableById(id);
    }

    public List<Lesson> findMonthlyLessons(TeacherMonthlyTimetable teacherMonthlyTimetable, int teacherId, int year,
            int month) {
        return dao.findMonthlyLessons(teacherMonthlyTimetable, teacherId, year, month);
    }
}
