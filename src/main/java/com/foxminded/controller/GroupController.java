package com.foxminded.controller;

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

import com.foxminded.domain.Group;
import com.foxminded.service.GroupService;

@Controller
public class GroupController {
    
    @Autowired
    private GroupService service;
    
    @GetMapping("/groups")
    public String viewGroupPage(Model model) {
        List<Group> groups = service.findAllGroups();
        model.addAttribute("groups", groups);
        return "groups/groups";
    }
    
    @GetMapping("/groups/{id}")
    public ModelAndView getGroupById(@RequestParam int id) {
        Group group = service.findGroupById(id);
        ModelAndView mav = new ModelAndView("groups/result");
        mav.addObject("group", group);
        return mav;
    }

    @GetMapping("groups/new-group")
    public String showNewForm(Model model) {
        Group group = new Group();
        model.addAttribute("group", group);
        return "groups/new-group";
    }

    @PostMapping("/groups")
    public String save(@ModelAttribute("group") Group group) {
        service.createGroup(group);
        return "redirect:/groups";
    }

    @PutMapping("/groups/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("groups/edit-group");
        Group group = service.findGroupById(id);
        mav.addObject("group", group);
        return mav;
    }

    @PutMapping("/groups")
    public String update(@ModelAttribute("group") Group group) {
        service.updateGroup(group);
        return "redirect:/groups";
    }

    @DeleteMapping("/groups/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        service.deleteGroup(id);
        return "redirect:/groups";
    }

}
