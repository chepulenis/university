package com.foxminded.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.university.domain.Group;
import com.foxminded.university.service.GroupService;

@Controller
public class GroupController {
    
    @Autowired
    private GroupService service;
    
    @GetMapping(value = "/groups", produces = "text/html")
    public String viewGroupPage(Model model) {
        List<Group> groups = service.findAllGroups();
        model.addAttribute("groups", groups);
        return "groups/groups";
    }
    
    @GetMapping(value = "/groups/{id}", produces = "text/html")
    public ModelAndView getGroupById(@RequestParam int id) {
        Group group = service.findGroupById(id);
        ModelAndView mav = new ModelAndView("groups/result");
        mav.addObject("group", group);
        return mav;
    }

    @GetMapping(value = "groups/new-group", produces = "text/html")
    public String showNewForm(Model model) {
        Group group = new Group();
        model.addAttribute("group", group);
        return "groups/new-group";
    }

    @PostMapping(value = "/groups", produces = "text/html")
    public String save(@ModelAttribute("group") Group group) {
        service.createGroup(group);
        return "redirect:/groups";
    }

    @PutMapping(value = "/groups/{id}", produces = "text/html")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("groups/edit-group");
        Group group = service.findGroupById(id);
        mav.addObject("group", group);
        return mav;
    }

    @PutMapping(value = "/groups", produces = "text/html")
    public String update(@ModelAttribute("group") Group group) {
        service.updateGroup(group);
        return "redirect:/groups";
    }

    @DeleteMapping(value = "/groups/{id}", produces = "text/html")
    public String delete(@PathVariable(name = "id") int id) {
        service.deleteGroup(id);
        return "redirect:/groups";
    }

}
