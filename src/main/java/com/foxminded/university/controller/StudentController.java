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

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;

@Controller
public class StudentController {
    
    @Autowired
    private StudentService service;

    @GetMapping(value = "/students", produces = "text/html")
    public String viewStudentsPage(Model model) {
        List<Student> students = service.findAllStudents();
        model.addAttribute("students", students);
        return "students/students";
    }
    
    @GetMapping(value = "/students/{id}", produces = "text/html")
    public ModelAndView getStudentById(@RequestParam int id) {
        Student student = service.findStudentById(id);
        ModelAndView mav = new ModelAndView("students/result");
        mav.addObject("student", student);
        return mav;
    }

    @GetMapping(value = "/students/new-student", produces = "text/html")
    public String showNewForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "students/new-student";
    }

    @PostMapping(value = "/students", produces = "text/html")
    public String save(@ModelAttribute("student") Student student) {
        service.createStudent(student);
        return "redirect:/students";
    }

    @PutMapping(value = "/students/{id}", produces = "text/html")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("students/edit-student");
        Student student = service.findStudentById(id);
        mav.addObject("student", student);
        return mav;
    }

    @PutMapping(value = "/students", produces = "text/html")
    public String update(@ModelAttribute("student") Student student) {
        service.updateStudent(student);
        return "redirect:/students";
    }

    @DeleteMapping(value = "/students/{id}", produces = "text/html")
    public String delete(@PathVariable(name = "id") int id) {
        service.deleteStudent(id);
        return "redirect:/students";
    }

}
