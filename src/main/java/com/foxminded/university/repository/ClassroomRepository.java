package com.foxminded.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foxminded.university.domain.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer>{
    
}
