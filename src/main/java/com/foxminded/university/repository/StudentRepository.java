package com.foxminded.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foxminded.university.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    
}
