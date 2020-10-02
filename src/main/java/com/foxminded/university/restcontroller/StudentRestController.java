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

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;

@RestController
public class StudentRestController {
    
    @Autowired
    private StudentService service;
    
    @GetMapping(value = "/students", produces = "application/json")
    public List<Student> getStudents() {
        List<Student> students = service.findAllStudents();
        return students;
    }
    
    @GetMapping(value = "/students/{id}", produces = "application/json")
    public Student findStudentById(@PathVariable int id) {
        return service.findStudentById(id);
    }
    
    @PostMapping(value = "/students", consumes = "application/json", produces = "application/json")
    public Student addStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }
    
    @PutMapping(value = "/students/{id}", consumes = "application/json", produces = "application/json")
    public Student updateStudent(@RequestBody Student student) {
        return service.updateStudent(student);
    }
    
    @DeleteMapping(value = "students/{id}", produces = "application/json")
    public void deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
    }

}
