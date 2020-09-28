package com.foxminded.university.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Student;
import com.foxminded.university.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student findStudentById(int id) {
        Student student = repository.findById(id).get();
        logger.info("Student {} by id {} found", student, id);
        return student;
    }

    public List<Student> findAllStudents() {
        List<Student> students = repository.findAll();
        logger.info("All students {} have been found", students);
        return students;
    }
    
    public void deleteStudent(int id) {
        logger.info("Student {} deleted", id);
        repository.deleteById(id);
    }
    
    public void updateStudent(Student student) {
        logger.info("Student {} updated", student);
        repository.save(student);
    }
    
    public void createStudent(Student student) {
        logger.info("Student {} created", student);
        repository.save(student);
    }
    
}
