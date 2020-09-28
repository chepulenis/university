package com.foxminded.university.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lesson;
import com.foxminded.university.domain.Subject;
import com.foxminded.university.domain.Teacher;
import com.foxminded.university.repository.LessonRepositoryImplementation;

@Service
public class LessonService {

    @Autowired
    private LessonRepositoryImplementation repository;
    private static final Logger logger = LoggerFactory.getLogger(LessonService.class);

    public Lesson findLessonById(int id) {
        Lesson lesson = repository.findLessonById(id);
        logger.info("Lesson {} by id {} has been found", lesson, id);
        return lesson;
    }

    public Classroom findClassroom(Lesson lesson) {
        Classroom classroom = repository.findClassroom(lesson);
        logger.info("Classroom {} for lesson {} has been found", classroom, lesson);
        return classroom;
    }

    public Teacher findTeacher(Lesson lesson) {
        Teacher teacher = repository.findTeacher(lesson);
        logger.info("Teacher {} for lesson {} has been found", teacher, lesson);
        return teacher;
    }

    public Subject findSubject(Lesson lesson) {
        Subject subject = repository.findSubject(lesson);
        logger.info("Subject {} for lesson {} has been found", subject, lesson);
        return subject;
    }

    public Group findGroup(Lesson lesson) {
        Group group = repository.findGroup(lesson);
        logger.info("Group {} for lesson {} has been found", group, lesson);
        return repository.findGroup(lesson);
    }

    public List<Lesson> findAllLessons() {
        List<Lesson> lessons = repository.findAllLessons();
        logger.info("All lessons {} have been found", lessons);
        return lessons;
    }

    public void createLesson(Lesson lesson) {
        logger.info("Lesson {} created", lesson);
        repository.createLesson(lesson);
    }

    public void updateLesson(Lesson lesson) {
        logger.info("Lesson {} updated", lesson);
        repository.updateLesson(lesson);
    }

    public void deleteLesson(int id) {
        logger.info("Lesson {} deleted", id);
        repository.deleteLesson(id);
    }

    public List<Lesson> findGroupDailyLessons(int groupId, Date date) {
        List<Lesson> lessons = repository.findGroupDailyLessons(groupId, date);
        logger.info("Lessons {} for group id {} by date {} have been found", lessons, groupId, date);
        return lessons;
    }

    public List<Lesson> findGroupMonthlyLessons(int groupId, int year, int month) {
        List<Lesson> lessons = repository.findGroupMonthlyLessons(groupId, year, month);
        logger.info("Lessons {} for group id {} by year {} and month {} have been found", lessons, groupId, year,
                month);
        return lessons;
    }

    public List<Lesson> findTeacherDailyLessons(int teacherId, Date date) {
        List<Lesson> lessons = repository.findTeacherDailyLessons(teacherId, date);
        logger.info("Lessons {} for teacher id {} by date {} have been found", lessons, teacherId, date);
        return lessons;
    }

    public List<Lesson> findTeacherMonthlyLessons(int teacherId, int year, int month) {
        List<Lesson> lessons = repository.findTeacherMonthlyLessons(teacherId, year, month);
        logger.info("Lessons {} for teacher id {} by year {} and month {} have been found", lessons, teacherId, year,
                month);
        return lessons;
    }

}
