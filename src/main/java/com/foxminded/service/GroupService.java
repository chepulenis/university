package com.foxminded.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.GroupDao;
import com.foxminded.domain.Group;
import com.foxminded.domain.Student;

@Service
public class GroupService {
    
    @Autowired
    private GroupDao dao;
    
    public Group findGroupById(int id) {
        return dao.findGroupById(id);
    }
    
    public List<Group> findAllGroups() {
        return dao.findAllGroups();
    }
    
    public boolean deleteGroup(Group group) {
        return dao.deleteGroup(group);
    }
    
    public boolean updateGroup(Group group) {
        return dao.updateGroup(group);
    }
    
    public boolean createGroup(Group group) {
        return dao.createGroup(group);
    }
    
    public List<Student> findStudentsGroup(Group group){
        return dao.findStudentsGroup(group);
    }

}
