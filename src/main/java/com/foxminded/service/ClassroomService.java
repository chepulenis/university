package com.foxminded.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.ClassroomDao;
import com.foxminded.domain.Classroom;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomDao dao;
    private static final Logger logger = LoggerFactory.getLogger(ClassroomService.class);

    public Classroom findClassroomById(int id) {
        Classroom classroom = dao.findClassroomById(id);
        logger.info("Classroom {} by id {} has been found", classroom, id);
        return classroom;
    }

    public List<Classroom> findAllClassrooms() {
        List <Classroom> classrooms = dao.findAllClassrooms();
        logger.info("All classrooms {} have been found", classrooms);
        return classrooms;
    }

    public boolean deleteClassroom(int id) {
        logger.info("Classroom {} deleted", id);
        return dao.deleteClassroom(id);
    }

    public boolean updateClassroom(Classroom classroom) {
        logger.info("Classroom {} updated", classroom);
        return dao.updateClassroom(classroom);
    }

    public boolean createClassroom(Classroom classroom) {
        logger.info("Classroom {} created", classroom);
        return dao.createClassroom(classroom);
    }

}
