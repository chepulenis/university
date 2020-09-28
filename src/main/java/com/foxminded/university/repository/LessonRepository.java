package com.foxminded.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foxminded.university.domain.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer>{
    
}
