package com.foxminded.university.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;
import com.foxminded.university.exception.StudentNotAssignedException;

@Service
public class StudentService {

    @Autowired
    private StudentDao dao;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student findStudentById(int id) {
        Student student = dao.findStudentById(id);
        logger.info("Student {} by id {} found", student, id);
        return student;
    }

    public List<Student> findAllStudents() {
        List<Student> students = dao.findAllStudents();
        logger.info("All students {} have been found", students);
        return students;
    }
    
    public boolean deleteStudent(int id) {
        logger.info("Student {} deleted", id);
        return dao.deleteStudent(id);
    }
    
    public boolean updateStudent(Student student) {
        logger.info("Student {} updated", student);
        return dao.updateStudent(student);
    }
    
    public boolean createStudent(Student student) {
        logger.info("Student {} created", student);
        return dao.createStudent(student);
    }
    
    public Group findStudentGroup(Student student){
        if (student.getGroup() == null) {
            throw new StudentNotAssignedException (student.getFirstName(), student.getLastName());
        }
        Group group = dao.findStudentGroup(student);
        logger.info("Student {} has been found in group {}", student, group);
        return group;
    }
    
}
