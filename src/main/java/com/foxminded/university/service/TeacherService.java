package com.foxminded.university.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Teacher;
import com.foxminded.university.repository.TeacherRepository;

@Service
public class TeacherService {
    
    @Autowired
    private TeacherRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);
    
    public Teacher findTeacherById(int id) {
        Teacher teacher = repository.findById(id).get();
        logger.info("Teacher {} by id {} has been found", teacher, id);
        return teacher;
    }
    
    public List<Teacher> findAllTeachers(){
        List <Teacher> teachers = repository.findAll();
        logger.info("All teachers {} have been found", teachers);
        return teachers;
    }
    
    public void deleteTeacher(int id) {
        logger.info("Teacher {} deleted", id);
        repository.deleteById(id);
    }
    
    public void updateTeacher(Teacher teacher) {
        logger.info("Teacher {} updated", teacher);
        repository.save(teacher);
    }
    
    public void createTeacher(Teacher teacher) {
        logger.info("Teacher {} created", teacher);
        repository.save(teacher);
    }
    
}
