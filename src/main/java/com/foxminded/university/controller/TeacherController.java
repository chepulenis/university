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

import com.foxminded.university.domain.Teacher;
import com.foxminded.university.service.TeacherService;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService service;

    @GetMapping(value = "/teachers", produces = "text/html")
    public String viewTeachersPage(Model model) {
        List<Teacher> teachers = service.findAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teachers/teachers";
    }
    
    @GetMapping(value = "/teachers/{id}", produces = "text/html")
    public ModelAndView getTeacherById(@RequestParam int id) {
        Teacher teacher = service.findTeacherById(id);
        ModelAndView mav = new ModelAndView("teachers/result");
        mav.addObject("teacher", teacher);
        return mav;
    }

    @GetMapping(value = "/teachers/new-teacher", produces = "text/html")
    public String showNewForm(Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "teachers/new-teacher";
    }

    @PostMapping(value = "/teachers", produces = "text/html")
    public String save(@ModelAttribute("teacher") Teacher teacher) {
        service.createTeacher(teacher);
        return "redirect:/teachers";
    }

    @PutMapping(value = "/teachers/{id}", produces = "text/html")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("teachers/edit-teacher");
        Teacher teacher = service.findTeacherById(id);
        mav.addObject("teacher", teacher);
        return mav;
    }

    @PutMapping(value = "/teachers", produces = "text/html")
    public String update(@ModelAttribute("teacher") Teacher teacher) {
        service.updateTeacher(teacher);
        return "redirect:/teachers";
    }

    @DeleteMapping(value = "/teachers/{id}", produces = "text/html")
    public String delete(@PathVariable(name = "id") int id) {
        service.deleteTeacher(id);
        return "redirect:/teachers";
    }

}
