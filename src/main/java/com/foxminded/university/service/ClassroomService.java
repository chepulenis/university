package com.foxminded.university.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.repository.ClassroomRepositoryImplementation;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepositoryImplementation repository;
    private static final Logger logger = LoggerFactory.getLogger(ClassroomService.class);

    public Classroom findClassroomById(int id) {
        Classroom classroom = repository.findClassroomById(id);
        logger.info("Classroom {} by id {} has been found", classroom, id);
        return classroom;
    }

    public List<Classroom> findAllClassrooms() {
        List <Classroom> classrooms = repository.findAllClassrooms();
        logger.info("All classrooms {} have been found", classrooms);
        return classrooms;
    }

    public void deleteClassroom(int id) {
        logger.info("Classroom {} deleted", id);
        repository.deleteClassroom(id);
    }

    public void updateClassroom(Classroom classroom) {
        logger.info("Classroom {} updated", classroom);
        repository.updateClassroom(classroom);
    }

    public void createClassroom(Classroom classroom) {
        logger.info("Classroom {} created", classroom);
        repository.createClassroom(classroom);
    }

}