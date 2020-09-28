package com.foxminded.university.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Group;
import com.foxminded.university.repository.GroupRepositoryImplementation;

@Service
public class GroupService {
    
    @Autowired
    private GroupRepositoryImplementation repository;
    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);
    
    public Group findGroupById(int id) {
        Group group = repository.findGroupById(id);
        logger.info("Group {} by id {} has been found", group, id);
        return group;
    }
    
    public List<Group> findAllGroups() {
        List<Group> groups = repository.findAllGroups();
        logger.info("All groups {} have been found", groups);
        return repository.findAllGroups();
    }
    
    public void deleteGroup(int id) {
        logger.info("Group {} deleted", id);
        repository.deleteGroup(id);
    }
    
    public void updateGroup(Group group) {
        logger.info("Group {} updated", group);
        repository.updateGroup(group);
    }
    
    public void createGroup(Group group) {
        logger.info("Group {} created", group);
        repository.createGroup(group);
    }
    
}
