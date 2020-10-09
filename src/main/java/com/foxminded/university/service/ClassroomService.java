package com.foxminded.university.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.repository.ClassroomRepository;

@Service
@Transactional
public class ClassroomService {

    private static final Logger logger = LoggerFactory.getLogger(ClassroomService.class);
    
    @Autowired
    private ClassroomRepository repository;

    public Classroom findClassroomById(int id) {
        Classroom classroom = repository.findById(id).orElse(null);
        logger.info("Classroom {} by id {} has been found", classroom, id);
        return classroom;
    }

    public List<Classroom> findAllClassrooms() {
        List <Classroom> classrooms = repository.findAll();
        logger.info("All classrooms {} have been found", classrooms);
        return classrooms;
    }

    public void deleteClassroom(int id) {
        logger.info("Classroom {} deleted", id);
        repository.deleteById(id);
    }

    public Classroom updateClassroom(Classroom classroom) {
        logger.info("Classroom {} updated", classroom);
        repository.save(classroom);
        return classroom;
    }

    public Classroom createClassroom(Classroom classroom) {
        logger.info("Classroom {} created", classroom);
        repository.save(classroom);
        return classroom;
    }

}
