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

import com.foxminded.university.domain.Teacher;
import com.foxminded.university.service.TeacherService;

@RestController
public class TeacherRestController {
    
    @Autowired
    private TeacherService service;
    
    @GetMapping(value = "/teachers", produces = "application/json")
    public List<Teacher> getTeacher() {
        List<Teacher> teachers = service.findAllTeachers();
        return teachers;
    }
    
    @GetMapping(value = "/teachers/{id}", produces = "application/json")
    public Teacher findTeacherById(@PathVariable int id) {
        return service.findTeacherById(id);
    }
    
    @PostMapping(value = "/teachers", consumes = "application/json", produces = "application/json")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return service.createTeacher(teacher);
    }
    
    @PutMapping(value = "/teachers/{id}", consumes = "application/json", produces = "application/json")
    public Teacher updateTeacher(@RequestBody Teacher teacher) {
        return service.updateTeacher(teacher);
    }
    
    @DeleteMapping(value = "teachers/{id}", produces = "application/json")
    public void deleteTeacher(@PathVariable int id) {
        service.deleteTeacher(id);
    }

}
