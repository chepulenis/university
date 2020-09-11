package com.foxminded.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.domain.Group;
import com.foxminded.service.GroupService;

@Controller
public class GroupController {
    
    @Autowired
    private GroupService service;
    
    @RequestMapping("/groups")
    public String viewGroupPage(Model model) {
        List<Group> groups = service.findAllGroups();
        model.addAttribute("groups", groups);
        return "group/groups";
    }

    @RequestMapping("/new_group")
    public String showNewForm(Model model) {
        Group group = new Group();
        model.addAttribute("group", group);
        return "group/new_group";
    }

    @RequestMapping(value = "/group_save", method = RequestMethod.POST)
    public String save(@ModelAttribute("group") Group group) {
        service.createGroup(group);
        return "redirect:/groups";
    }

    @RequestMapping("/group_edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("group/group_edit");
        Group group = service.findGroupById(id);
        mav.addObject("group", group);
        return mav;
    }

    @RequestMapping(value = "/group_update", method = RequestMethod.POST)
    public String update(@ModelAttribute("group") Group group) {
        service.updateGroup(group);
        return "redirect:/groups";
    }

    @RequestMapping("/group_delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        service.deleteGroup(id);
        return "redirect:/groups";
    }

}
