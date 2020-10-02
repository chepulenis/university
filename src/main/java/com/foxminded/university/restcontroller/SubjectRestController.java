package com.foxminded.university.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.university.domain.Subject;
import com.foxminded.university.service.SubjectService;

@RestController
public class SubjectRestController {

    @Autowired
    private SubjectService service;

    @GetMapping(value = "/subjects", produces = "application/json")
    public List<Subject> getSubjects() {
        List<Subject> subjects = service.findAllSubjects();
        return subjects;
    }

    @GetMapping(value = "/subjects/{id}", produces = "application/json")
    public Subject findSubjectById(@PathVariable int id) {
        return service.findSubjectById(id);
    }

    @PostMapping(value = "/subjects", consumes = "application/json", produces = "application/json")
    public Subject addSubject(@RequestBody Subject subject) {
        return service.createSubject(subject);
    }

    @PutMapping(value = "/subjects/{id}", consumes = "application/json", produces = "application/json")
    public Subject updateSubject(@RequestBody Subject subject) {
        return service.updateSubject(subject);
    }

    @DeleteMapping(value = "subjects/{id}", produces = "application/json")
    public void deleteSubject(@PathVariable int id) {
        service.deleteSubject(id);
    }

}
