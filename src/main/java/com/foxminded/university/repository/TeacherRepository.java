package com.foxminded.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foxminded.university.domain.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
    
}
