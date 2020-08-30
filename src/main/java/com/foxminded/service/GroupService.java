package com.foxminded.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.dao.GroupDao;
import com.foxminded.domain.Group;
import com.foxminded.domain.Student;

@Service
public class GroupService {
    
    @Autowired
    private GroupDao dao;
    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);
    
    public Group findGroupById(int id) {
        Group group = dao.findGroupById(id);
        logger.info("Group {} by id {} founded", group, id);
        return group;
    }
    
    public List<Group> findAllGroups() {
        List<Group> groups = dao.findAllGroups();
        logger.info("All groups {} founded", groups);
        return dao.findAllGroups();
    }
    
    public boolean deleteGroup(Group group) {
        logger.info("Group {} deleted", group);
        return dao.deleteGroup(group);
    }
    
    public boolean updateGroup(Group group) {
        logger.info("Group {} updated", group);
        return dao.updateGroup(group);
    }
    
    public boolean createGroup(Group group) {
        logger.info("Group {} created", group);
        return dao.createGroup(group);
    }
    
    public List<Student> findStudentsGroup(Group group){
        List<Student> students = dao.findStudentsGroup(group);
        logger.info("Students {} for group {} have been found", students, group);
        return students;
    }

}
