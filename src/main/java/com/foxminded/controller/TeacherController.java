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

import com.foxminded.domain.Teacher;
import com.foxminded.service.TeacherService;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService service;

    @RequestMapping("/teachers")
    public String viewTeachersPage(Model model) {
        List<Teacher> teachers = service.findAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teacher/teachers";
    }

    @RequestMapping("/new_teacher")
    public String showNewForm(Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "teacher/new_teacher";
    }

    @RequestMapping(value = "/teacher_save", method = RequestMethod.POST)
    public String save(@ModelAttribute("teacher") Teacher teacher) {
        service.createTeacher(teacher);
        return "redirect:/teachers";
    }

    @RequestMapping("/teacher_edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("teacher/teacher_edit");
        Teacher teacher = service.findTeacherById(id);
        mav.addObject("teacher", teacher);
        return mav;
    }

    @RequestMapping(value = "/teacher_update", method = RequestMethod.POST)
    public String update(@ModelAttribute("teacher") Teacher teacher) {
        service.updateTeacher(teacher);
        return "redirect:/teachers";
    }

    @RequestMapping("/teacher_delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        service.deleteTeacher(id);
        return "redirect:/teachers";
    }

}
