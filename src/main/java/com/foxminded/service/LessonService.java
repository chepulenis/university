package com.foxminded.service;

import java.util.Date;
import java.util.List;

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

    public Lesson findLessonById(int id) {
        Lesson lesson = dao.findLessonById(id);
        logger.info("Lesson {} by id {} has been found", lesson, id);
        return lesson;
    }

    public Classroom findClassroom(Lesson lesson) {
        Classroom classroom = dao.findClassroom(lesson);
        logger.info("Classroom {} for lesson {} has been found", classroom, lesson);
        return classroom;
    }

    public Teacher findTeacher(Lesson lesson) {
        Teacher teacher = dao.findTeacher(lesson);
        logger.info("Teacher {} for lesson {} has been found", teacher, lesson);
        return teacher;
    }

    public Subject findSubject(Lesson lesson) {
        Subject subject = dao.findSubject(lesson);
        logger.info("Subject {} for lesson {} has been found", subject, lesson);
        return subject;
    }

    public Group findGroup(Lesson lesson) {
        Group group = dao.findGroup(lesson);
        logger.info("Group {} for lesson {} has been found", group, lesson);
        return dao.findGroup(lesson);
    }

    public List<Lesson> findAllLessons() {
        List<Lesson> lessons = dao.findAllLessons();
        logger.info("All lessons {} have been found", lessons);
        return lessons;
    }

    public boolean createLesson(Lesson lesson) {
        logger.info("Lesson {} created", lesson);
        return dao.createLesson(lesson);
    }

    public boolean updateLesson(Lesson lesson) {
        logger.info("Lesson {} updated", lesson);
        return dao.updateLesson(lesson);
    }

    public boolean deleteLesson(int id) {
        logger.info("Lesson {} deleted", id);
        return dao.deleteLesson(id);
    }

    public List<Lesson> findGroupDailyLessons(int groupId, Date date) {
        List<Lesson> lessons = dao.findGroupDailyLessons(groupId, date);
        logger.info("Lessons {} for group id {} by date {} have been found", lessons, groupId, date);
        return lessons;
    }

    public List<Lesson> findGroupMonthlyLessons(int groupId, int year, int month) {
        List<Lesson> lessons = dao.findGroupMonthlyLessons(groupId, year, month);
        logger.info("Lessons {} for group id {} by year {} and month {} have been found", lessons, groupId, year,
                month);
        return lessons;
    }

    public List<Lesson> findTeacherDailyLessons(int teacherId, Date date) {
        List<Lesson> lessons = dao.findTeacherDailyLessons(teacherId, date);
        logger.info("Lessons {} for teacher id {} by date {} have been found", lessons, teacherId, date);
        return lessons;
    }

    public List<Lesson> findTeacherMonthlyLessons(int teacherId, int year, int month) {
        List<Lesson> lessons = dao.findTeacherMonthlyLessons(teacherId, year, month);
        logger.info("Lessons {} for teacher id {} by year {} and month {} have been found", lessons, teacherId, year,
                month);
        return lessons;
    }

}
