package com.foxminded.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.StudentDao;
import com.foxminded.domain.Group;
import com.foxminded.domain.Student;

@Service
public class StudentService {

    @Autowired
    private StudentDao dao;

    public Student findStudentById(int id) {
        return dao.findStudentById(id);
    }

    public List<Student> findAllStudents() {
        return dao.findAllStudents();
    }
    
    public boolean deleteStudent(Student student) {
        return dao.deleteStudent(student);
    }
    
    public boolean updateStudent(Student student) {
        return dao.updateStudent(student);
    }
    
    public boolean createStudent(Student student) {
        return dao.createStudent(student);
    }
    
    public Group findStudentGroup(Student student) {
        return dao.findStudentGroup(student);
    }

}
