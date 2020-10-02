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

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.service.ClassroomService;

@Controller
public class ClassroomController {
    
    @Autowired
    private ClassroomService service;
    
    @GetMapping(value = "/classrooms", produces = "text/html")
    public String getClassrooms(Model model) {
        List<Classroom> classrooms = service.findAllClassrooms();
        model.addAttribute("classrooms", classrooms);
        return "classrooms/classrooms";
    }
    
    @GetMapping(value = "/classrooms/{id}", produces = "text/html")
    public ModelAndView getClassroomById(@RequestParam int id) {
        Classroom classroom = service.findClassroomById(id);
        ModelAndView mav = new ModelAndView("classrooms/result");
        mav.addObject("classroom", classroom);
        return mav;
    }

    @GetMapping(value = "/classrooms/new-classroom", produces = "text/html")
    public String showNewForm(Model model) {
        Classroom classroom = new Classroom();
        model.addAttribute("classroom", classroom);
        return "classrooms/new-classroom";
    }

    @PostMapping(value = "/classrooms", produces = "text/html")
    public String save(@ModelAttribute("classroom") Classroom classroom) {
        service.createClassroom(classroom);
        return "redirect:/classrooms";
    }

    @PutMapping(value = "/classrooms/{id}", produces = "text/html")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("classrooms/edit-classroom");
        Classroom classroom = service.findClassroomById(id);
        mav.addObject("classroom", classroom);
        return mav;
    }

    @PutMapping(value = "/classrooms", produces = "text/html")
    public String update(@ModelAttribute("classroom") Classroom classroom) {
        service.updateClassroom(classroom);
        return "redirect:/classrooms";
    }

    @DeleteMapping(value = "/classrooms/{id}", produces = "text/html")
    public String delete(@PathVariable(name = "id") int id) {
        service.deleteClassroom(id);
        return "redirect:/classrooms";
    }
    
}
