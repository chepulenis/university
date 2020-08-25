package com.foxminded.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.SubjectDao;
import com.foxminded.domain.Subject;
import com.foxminded.domain.Teacher;

@Service
public class SubjectService {

    @Autowired
    private SubjectDao dao;
    
    public Subject findSubjectById(int id) {
        return dao.findSubjectById(id);
    }

    public List<Subject> findAllSubjects() {
        return dao.findAllSubjects();
    }
    
    public boolean deleteSubject(Subject subject) {
        return dao.deleteSubject(subject);
    }
    
    public boolean updateSubject(Subject subject) {
        return dao.updateSubject(subject);
    }
    
    public boolean createSubject(Subject subject) {
        return dao.createSubject(subject);
    }
    
    public List<Teacher> findSubjectTeachers(Subject subject){
        return dao.findSubjectTeachers(subject);
    }
    
}
