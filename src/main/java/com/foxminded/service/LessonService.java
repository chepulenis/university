package com.foxminded.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.LessonDao;
import com.foxminded.domain.Classroom;
import com.foxminded.domain.Group;
import com.foxminded.domain.Lesson;
import com.foxminded.domain.Subject;
import com.foxminded.domain.Teacher;

@Service
public class LessonService {

    @Autowired
    private LessonDao dao;
    
    public Lesson findLessonById (int id) {
        return dao.findLessonById(id);
    }
    
    public Classroom findClassroom(Lesson lesson) {
        return dao.findClassroom(lesson);
    }
    
    public Teacher findTeacher(Lesson lesson) {
        return dao.findTeacher(lesson);
    }
    
    public Subject findSubject(Lesson lesson) {
        return dao.findSubject(lesson);
    }
    
    public Group findGroup(Lesson lesson) {
        return dao.findGroup(lesson);
    }
    
    
}
