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

import com.foxminded.domain.Subject;
import com.foxminded.service.SubjectService;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService service;
    
    @RequestMapping("/subjects")
    public String viewSubjectPage(Model model) {
        List<Subject> subjects = service.findAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subject/subjects";
    }

    @RequestMapping("/new_subject")
    public String showNewForm(Model model) {
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "subject/new_subject";
    }

    @RequestMapping(value = "/subject_save", method = RequestMethod.POST)
    public String save(@ModelAttribute("subject") Subject subject) {
        service.createSubject(subject);
        return "redirect:/subjects";
    }

    @RequestMapping("/subject_edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("subject/subject_edit");
        Subject subject = service.findSubjectById(id);
        mav.addObject("subject", subject);
        return mav;
    }

    @RequestMapping(value = "/subject_update", method = RequestMethod.POST)
    public String update(@ModelAttribute("subject") Subject subject) {
        service.updateSubject(subject);
        return "redirect:/subjects";
    }

    @RequestMapping("/subject_delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        service.deleteSubject(id);
        return "redirect:/subjects";
    }
    
}
