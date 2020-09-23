package com.foxminded.university.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.dao.TeacherDao;
import com.foxminded.university.domain.Subject;
import com.foxminded.university.domain.Teacher;

@Service
public class TeacherService {
    
    @Autowired
    private TeacherDao dao;
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);
    
    public Teacher findTeacherById(int id) {
        Teacher teacher = dao.findTeacherById(id);;
        logger.info("Teacher {} by id {} has been found", teacher, id);
        return teacher;
    }
    
    public List<Teacher> findAllTeachers(){
        List <Teacher> teachers = dao.findAllTeachers();
        logger.info("All teachers {} have been found", teachers);
        return teachers;
    }
    
    public boolean deleteTeacher(int id) {
        logger.info("Teacher {} deleted", id);
        return dao.deleteTeacher(id);
    }
    
    public boolean updateTeacher(Teacher teacher) {
        logger.info("Teacher {} updated", teacher);
        return dao.updateTeacher(teacher);
    }
    
    public boolean createTeacher(Teacher teacher) {
        logger.info("Teacher {} created", teacher);
        return dao.createTeacher(teacher);
    }
    
    public List<Subject> findTeacherSubjects(Teacher teacher) {
        List<Subject> subjects = dao.findTeacherSubjects(teacher);
        logger.info("Subjects {} for teacher {} have been found", subjects, teacher);
        return subjects;
    }
    
}
