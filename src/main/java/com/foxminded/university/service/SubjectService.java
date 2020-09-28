package com.foxminded.university.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Subject;
import com.foxminded.university.repository.SubjectRepositoryImplementation;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepositoryImplementation repository;
    private static final Logger logger = LoggerFactory.getLogger(SubjectService.class);
    
    public Subject findSubjectById(int id) {
        Subject subject = repository.findSubjectById(id); 
        logger.info("Subject {} by id {} has been found", subject, id);
        return subject;
    }

    public List<Subject> findAllSubjects() {
        List<Subject> subjects = repository.findAllSubjects();
        logger.info("All subjects {} have been found", subjects);
        return subjects;
    }
    
    public void deleteSubject(int id) {
        logger.info("Subject {} deleted", id);
        repository.deleteSubject(id);
    }
    
    public void updateSubject(Subject subject) {
        logger.info("Subject {} updated", subject);
        repository.updateSubject(subject);
    }
    
    public void createSubject(Subject subject) {
        logger.info("Subject {} created", subject);
        repository.createSubject(subject);
    }
    
}