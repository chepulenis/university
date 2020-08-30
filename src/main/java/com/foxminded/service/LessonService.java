package com.foxminded.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(LessonService.class);
    
    public Lesson findLessonById (int id) {
        Lesson lesson = dao.findLessonById(id);
        logger.info("Lesson {} by id {} founded", lesson, id);
        return lesson;
    }
    
    public Classroom findClassroom(Lesson lesson) {
        Classroom classroom = dao.findClassroom(lesson);
        logger.info("Classroom {} for lesson {} founded", classroom, lesson);
        return classroom;
    }
    
    public Teacher findTeacher(Lesson lesson) {
        Teacher teacher = dao.findTeacher(lesson);
        logger.info("Teacher {} for lesson {} founded", teacher, lesson);
        return teacher;
    }
    
    public Subject findSubject(Lesson lesson) {
        Subject subject = dao.findSubject(lesson);
        logger.info("Subject {} for lesson {} founded", subject, lesson);
        return subject;
    }
    
    public Group findGroup(Lesson lesson) {
        Group group = dao.findGroup(lesson);
        logger.info("Group {} for lesson {} founded", group, lesson);
        return dao.findGroup(lesson);
    }
    
}
