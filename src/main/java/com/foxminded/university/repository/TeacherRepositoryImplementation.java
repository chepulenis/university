package com.foxminded.university.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.domain.Teacher;

@Component
public class TeacherRepositoryImplementation {

    @Autowired
    private TeacherRepository repository;
    
    public Teacher findTeacherById(int id) {
        return repository.findById(id).get();
    }

    public List<Teacher> findAllTeachers() {
        return repository.findAll();
    }

    public void deleteTeacher(int id) {
        repository.deleteById(id);
    }

    public void updateTeacher(Teacher teacher) {
        repository.save(teacher);
    }

    public void createTeacher(Teacher teacher) {
        repository.save(teacher);
    }

}
