package com.foxminded.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.ClassroomDao;
import com.foxminded.domain.Classroom;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomDao dao;
    
    public Classroom findClassroomById(int id) {
        return dao.findClassroomById(id);
    }
    
    public List<Classroom> findAllClassrooms() {
        return dao.findAllClassrooms();
    }
    
    public boolean deleteClassrom(Classroom classroom) {
        return dao.deleteClassrom(classroom);
    }
    
    public boolean updateClassroom(Classroom classroom) {
        return dao.updateClassroom(classroom);
    }
    
    public boolean createClassroom(Classroom classroom) {
        return dao.createClassroom(classroom);
        
    }
    
}
