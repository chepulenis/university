package com.foxminded.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.StudentDao;
import com.foxminded.domain.Group;
import com.foxminded.domain.Student;
import com.foxminded.exception.StudentNotAssignedException;

@Service
public class StudentService {

    @Autowired
    private StudentDao dao;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student findStudentById(int id) {
        Student student = dao.findStudentById(id);
        logger.info("Student {} by id {} founded", student, id);
        return student;
    }

    public List<Student> findAllStudents() {
        List<Student> students = dao.findAllStudents();
        logger.info("All students {} founded", students);
        return students;
    }
    
    public boolean deleteStudent(Student student) {
        logger.info("Student {} deleted", student);
        return dao.deleteStudent(student);
    }
    
    public boolean updateStudent(Student student) {
        logger.info("Student {} updated", student);
        return dao.updateStudent(student);
    }
    
    public boolean createStudent(Student student) {
        logger.info("Student {} created", student);
        return dao.createStudent(student);
    }
    
    public Group findStudentGroup(Student student) throws StudentNotAssignedException {
        if (student.getGroup() == null) {
            logger.error("Student is not assigned to any group.");
            throw new StudentNotAssignedException ("Student is not assigned to any group.");
        }
        Group group = dao.findStudentGroup(student);
        logger.info("Student {} founded in group {}", student, group);
        return group;
    }

}
