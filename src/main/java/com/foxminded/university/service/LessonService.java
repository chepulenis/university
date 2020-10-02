package com.foxminded.university.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Lesson;
import com.foxminded.university.repository.LessonRepository;

@Service
public class LessonService {

    private static final Logger logger = LoggerFactory.getLogger(LessonService.class);
    
    @Autowired
    private LessonRepository repository;
    
    public Lesson findLessonById(int id) {
        Lesson lesson = repository.findById(id).get();
        logger.info("Lesson {} by id {} has been found", lesson, id);
        return lesson;
    }

    public List<Lesson> findAllLessons() {
        List<Lesson> lessons = repository.findAll();
        logger.info("All lessons {} have been found", lessons);
        return lessons;
    }

    public Lesson createLesson(Lesson lesson) {
        logger.info("Lesson {} created", lesson);
        repository.save(lesson);
        return lesson;
    }

    public Lesson updateLesson(Lesson lesson) {
        logger.info("Lesson {} updated", lesson);
        repository.save(lesson);
        return lesson;
    }

    public void deleteLesson(int id) {
        logger.info("Lesson {} deleted", id);
        repository.deleteById(id);
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
