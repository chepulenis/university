package com.foxminded.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.SubjectDao;
import com.foxminded.domain.Subject;
import com.foxminded.domain.Teacher;

@Service
public class SubjectService {

    @Autowired
    private SubjectDao dao;
    private static final Logger logger = LoggerFactory.getLogger(SubjectService.class);
    
    public Subject findSubjectById(int id) {
        Subject subject = dao.findSubjectById(id); 
        logger.info("Subject {} by id {} founded", subject, id);
        return subject;
    }

    public List<Subject> findAllSubjects() {
        List<Subject> subjects = dao.findAllSubjects();
        logger.info("All subjects {} founded", subjects);
        return subjects;
    }
    
    public boolean deleteSubject(Subject subject) {
        logger.info("Subject {} deleted", subject);
        return dao.deleteSubject(subject);
    }
    
    public boolean updateSubject(Subject subject) {
        logger.info("Subject {} updated", subject);
        return dao.updateSubject(subject);
    }
    
    public boolean createSubject(Subject subject) {
        logger.info("Subject {} created", subject);
        return dao.createSubject(subject);
    }
    
    public List<Teacher> findSubjectTeachers(Subject subject){
        List<Teacher> teachers = dao.findSubjectTeachers(subject);
        logger.info("Teachers {} for subject {} founded", teachers, subject);
        return teachers;
    }
    
}
