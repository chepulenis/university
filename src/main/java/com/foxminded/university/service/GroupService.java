package com.foxminded.university.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Group;
import com.foxminded.university.repository.GroupRepository;

@Service
public class GroupService {

    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);
    
    @Autowired
    private GroupRepository repository;
    
    public Group findGroupById(int id) {
        Group group = repository.findById(id).get();
        logger.info("Group {} by id {} has been found", group, id);
        return group;
    }
    
    public List<Group> findAllGroups() {
        List<Group> groups = repository.findAll();
        logger.info("All groups {} have been found", groups);
        return groups;
    }
    
    public void deleteGroup(int id) {
        logger.info("Group {} deleted", id);
        repository.deleteById(id);
    }
    
    public void updateGroup(Group group) {
        logger.info("Group {} updated", group);
        repository.save(group);
    }
    
    public void createGroup(Group group) {
        logger.info("Group {} created", group);
        repository.save(group);
    }
    
}
