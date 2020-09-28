package com.foxminded.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foxminded.university.domain.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{
    
}
