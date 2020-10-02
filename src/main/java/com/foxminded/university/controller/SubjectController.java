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

import com.foxminded.university.domain.Subject;
import com.foxminded.university.service.SubjectService;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService service;
    
    @GetMapping(value = "/subjects", produces = "text/html")
    public String viewSubjectPage(Model model) {
        List<Subject> subjects = service.findAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subjects/subjects";
    }
    
    @GetMapping(value = "/subjects/{id}", produces = "text/html")
    public ModelAndView getSubjectById(@RequestParam int id) {
        Subject subject = service.findSubjectById(id);
        ModelAndView mav = new ModelAndView("subjects/result");
        mav.addObject("subject", subject);
        return mav;
    }

    @GetMapping(value = "/subjects/new-subject", produces = "text/html")
    public String showNewForm(Model model) {
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "subject/new-subject";
    }

    @PostMapping(value = "/subjects", produces = "text/html")
    public String save(@ModelAttribute("subject") Subject subject) {
        service.createSubject(subject);
        return "redirect:/subjects";
    }

    @PutMapping(value = "/subjects/{id}", produces = "text/html")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("subject/edit-subject");
        Subject subject = service.findSubjectById(id);
        mav.addObject("subject", subject);
        return mav;
    }

    @PutMapping(value = "/subjects", produces = "text/html")
    public String update(@ModelAttribute("subject") Subject subject) {
        service.updateSubject(subject);
        return "redirect:/subjects";
    }

    @DeleteMapping(value = "/subjects/{id}", produces = "text/html")
    public String delete(@PathVariable(name = "id") int id) {
        service.deleteSubject(id);
        return "redirect:/subjects";
    }
    
}
