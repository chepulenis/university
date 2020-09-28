package com.foxminded.university.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.university.domain.Subject;

@Component
@Transactional
public class SubjectRepositoryImplementation {

    @Autowired
    private SubjectRepository repository;

    public Subject findSubjectById(int id) {
        return repository.findById(id).get();
    }

    public List<Subject> findAllSubjects() {
        return repository.findAll();
    }

    public void deleteSubject(int id) {
        repository.deleteById(id);
    }

    public void updateSubject(Subject subject) {
        repository.save(subject);
    }

    public void createSubject(Subject subject) {
        repository.save(subject);
    }
}
