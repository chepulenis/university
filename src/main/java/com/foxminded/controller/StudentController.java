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

import com.foxminded.domain.Student;
import com.foxminded.service.StudentService;

@Controller
public class StudentController {
    
    @Autowired
    private StudentService service;

    @RequestMapping("/students")
    public String viewStudentsPage(Model model) {
        List<Student> students = service.findAllStudents();
        model.addAttribute("students", students);
        return "student/students";
    }

    @RequestMapping("/new_student")
    public String showNewForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/new_student";
    }

    @RequestMapping(value = "/student_save", method = RequestMethod.POST)
    public String save(@ModelAttribute("student") Student student) {
        service.createStudent(student);
        return "redirect:/students";
    }

    @RequestMapping("/student_edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("student/student_edit");
        Student student = service.findStudentById(id);
        mav.addObject("student", student);
        return mav;
    }

    @RequestMapping(value = "/student_update", method = RequestMethod.POST)
    public String update(@ModelAttribute("student") Student student) {
        service.updateStudent(student);
        return "redirect:/students";
    }

    @RequestMapping("/student_delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        service.deleteStudent(id);
        return "redirect:/students";
    }

}
