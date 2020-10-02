package com.foxminded.university.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.university.domain.Group;
import com.foxminded.university.service.GroupService;

@RestController
public class GroupRestController {
    
    @Autowired
    private GroupService service;
    
    @GetMapping(value = "/groups", produces = "application/json")
    public List<Group> getGroups() {
        List<Group> groups = service.findAllGroups();
        return groups;
    }
    
    @GetMapping(value = "/groups/{id}", produces = "application/json")
    public Group findGroupById(@PathVariable int id) {
        return service.findGroupById(id);
    }
    
    @PostMapping(value = "/groups", consumes = "application/json", produces = "application/json")
    public Group addGroup(@RequestBody Group group) {
        return service.createGroup(group);
    }
    
    @PutMapping(value = "/groups/{id}", consumes = "application/json", produces = "application/json")
    public Group updateGroup(@RequestBody Group group) {
        return service.updateGroup(group);
    }
    
    @DeleteMapping(value = "groups/{id}", produces = "application/json")
    public void deleteGroup(@PathVariable int id) {
        service.deleteGroup(id);
    }

}
