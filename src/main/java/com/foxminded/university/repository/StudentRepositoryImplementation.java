package com.foxminded.university.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.university.domain.Student;

@Component
@Transactional
public class StudentRepositoryImplementation {

    @Autowired
    private StudentRepository repository;

    public Student findStudentById(int id) {
        return repository.findById(id).get();
    }

    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    public void deleteStudent(int id) {
        repository.deleteById(id);
    }

    public void updateStudent(Student student) {
        repository.save(student);
    }

    public void createStudent(Student student) {
        repository.save(student);
    }

}
