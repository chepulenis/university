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

import com.foxminded.domain.Classroom;
import com.foxminded.service.ClassroomService;

@Controller
public class ClassroomController {
    
    @Autowired
    private ClassroomService service;
    
    @RequestMapping("/classrooms")
    public String viewClassroomPage(Model model) {
        List<Classroom> classrooms = service.findAllClassrooms();
        model.addAttribute("classrooms", classrooms);
        return "classroom/classrooms";
    }

    @RequestMapping("/new_classroom")
    public String showNewForm(Model model) {
        Classroom classroom = new Classroom();
        model.addAttribute("classroom", classroom);
        return "classroom/new_classroom";
    }

    @RequestMapping(value = "/classroom_save", method = RequestMethod.POST)
    public String save(@ModelAttribute("classroom") Classroom classroom) {
        service.createClassroom(classroom);
        return "redirect:/classrooms";
    }

    @RequestMapping("/classroom_edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("classroom/classroom_edit");
        Classroom classroom = service.findClassroomById(id);
        mav.addObject("classroom", classroom);
        return mav;
    }

    @RequestMapping(value = "/classroom_update", method = RequestMethod.POST)
    public String update(@ModelAttribute("classroom") Classroom classroom) {
        service.updateClassroom(classroom);
        return "redirect:/classrooms";
    }

    @RequestMapping("/classroom_delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        service.deleteClassroom(id);
        return "redirect:/classrooms";
    }

}
