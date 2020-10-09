package com.foxminded.university.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Subject;
import com.foxminded.university.repository.SubjectRepository;

@Service
public class SubjectService {

    private static final Logger logger = LoggerFactory.getLogger(SubjectService.class);
    
    @Autowired
    private SubjectRepository repository;
    
    public Subject findSubjectById(int id) {
        Subject subject = repository.findById(id).orElse(null); 
        logger.info("Subject {} by id {} has been found", subject, id);
        return subject;
    }

    public List<Subject> findAllSubjects() {
        List<Subject> subjects = repository.findAll();
        logger.info("All subjects {} have been found", subjects);
        return subjects;
    }
    
    public void deleteSubject(int id) {
        logger.info("Subject {} deleted", id);
        repository.deleteById(id);
    }
    
    public Subject updateSubject(Subject subject) {
        logger.info("Subject {} updated", subject);
        repository.save(subject);
        return subject;
    }
    
    public Subject createSubject(Subject subject) {
        logger.info("Subject {} created", subject);
        repository.save(subject);
        return subject;
    }
    
}
