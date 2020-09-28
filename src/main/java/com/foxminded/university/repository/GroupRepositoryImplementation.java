package com.foxminded.university.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.university.domain.Group;

@Component
@Transactional
public class GroupRepositoryImplementation {

    @Autowired
    private GroupRepository repository;

    public Group findGroupById(int id) {
        return repository.findById(id).get();
    }

    public List<Group> findAllGroups() {
        return repository.findAll();
    }

    public void deleteGroup(int id) {
        repository.deleteById(id);
    }

    public void updateGroup(Group group) {
        repository.save(group);
    }

    public void createGroup(Group group) {
        repository.save(group);
    }
    
}
