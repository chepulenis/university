package com.foxminded.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.TeacherDao;
import com.foxminded.domain.Subject;
import com.foxminded.domain.Teacher;

@Service
public class TeacherService {
    
    @Autowired
    private TeacherDao dao;
    
    public Teacher findTeacherById(int id) {
        return dao.findTeacherById(id);
    }
    
    public List<Teacher> findAllTeachers(){
        return dao.findAllTeachers();
    }
    
    public boolean deleteTeacher(Teacher teacher) {
        return dao.deleteTeacher(teacher);
    }
    
    public boolean updateTeacher(Teacher teacher) {
        return dao.updateTeacher(teacher);
    }
    
    public boolean createTeacher(Teacher teacher) {
        return dao.createTeacher(teacher);
    }
    
    public List<Subject> findTeacherSubjects(Teacher teacher) {
        return dao.findTeacherSubjects(teacher);
    }

}
