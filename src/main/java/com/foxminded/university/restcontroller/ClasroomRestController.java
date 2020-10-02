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

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.service.ClassroomService;

@RestController
public class ClasroomRestController {
    
    @Autowired
    private ClassroomService service;
    
    @GetMapping(value = "/classrooms", produces = "application/json")
    public List<Classroom> getClassrooms() {
        List<Classroom> classrooms = service.findAllClassrooms();
        return classrooms;
    }
    
    @GetMapping(value = "/classrooms/{id}", produces = "application/json")
    public Classroom findClassroomById(@PathVariable int id) {
        return service.findClassroomById(id);
    }
    
    @PostMapping(value = "/classrooms", consumes = "application/json", produces = "application/json")
    public Classroom addClassroom(@RequestBody Classroom classroom) {
        return service.createClassroom(classroom);
    }
    
    @PutMapping(value = "/classrooms/{id}", consumes = "application/json", produces = "application/json")
    public Classroom updateClassroom(@RequestBody Classroom classroom) {
        return service.updateClassroom(classroom);
    }
    
    @DeleteMapping(value = "classrooms/{id}", produces = "application/json")
    public void deleteClassroom(@PathVariable int id) {
        service.deleteClassroom(id);
    }
    
}
