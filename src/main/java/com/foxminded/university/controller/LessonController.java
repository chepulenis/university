package com.foxminded.university.controller;

import java.sql.Date;
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

import com.foxminded.university.domain.Lesson;
import com.foxminded.university.service.LessonService;

@Controller
public class LessonController {
    
    @Autowired
    private LessonService service;

    @GetMapping("/lessons")
    public String viewLessonsPage(Model model) {
        List<Lesson> lessons = service.findAllLessons();
        model.addAttribute("lessons", lessons);
        return "lessons/lessons";
    }
    
    @GetMapping("/lessons/{id}")
    public ModelAndView getLessonById(@RequestParam int id) {
        Lesson lesson = service.findLessonById(id);
        ModelAndView mav = new ModelAndView("lessons/result");
        mav.addObject("lesson", lesson);
        return mav;
    }

    @GetMapping("/lessons/new-lesson")
    public String showNewForm(Model model) {
        Lesson lesson = new Lesson();
        model.addAttribute("lesson", lesson);
        return "lessons/new-lesson";
    }

    @PostMapping("/lessons")
    public String save(@ModelAttribute("lesson") Lesson lesson) {
        service.createLesson(lesson);
        return "redirect:/lessons";
    }

    @PutMapping("/lessons/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("lessons/edit-lesson");
        Lesson lesson = service.findLessonById(id);
        mav.addObject("lesson", lesson);
        return mav;
    }

    @PutMapping("/lessons")
    public String update(@ModelAttribute("lesson") Lesson lesson) {
        service.updateLesson(lesson);
        return "redirect:/lessons";
    }

    @DeleteMapping("/lessons/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        service.deleteLesson(id);
        return "redirect:/lessons";
    }
    
    @GetMapping("/group-timetable")
    public String viewGroupDailyTimetablePage() {
        return "timetable/group-timetable";
    }
    
    @GetMapping("/teacher-timetable")
    public String viewTeacherDailyTimetablePage() {
        return "timetable/teacher-timetable";
    }
    
    @PostMapping(value = "/group-timetable-result", params = "date")
    public String viewGroupTimetablePage(Model model, @RequestParam(name = "groupId") int groupId, @RequestParam(name = "date") Date date) {
        List<Lesson> lessons = service.findGroupDailyLessons(groupId, date);
        model.addAttribute("lessons", lessons);
        return "timetable/group-timetable-result";
    }
    
    @PostMapping(value = "/group-timetable-result", params = "month")
    public String viewGroupTimetablePage(Model model, @RequestParam(name = "groupId") int groupId,  @RequestParam(name = "month") String yearAndMonth) {
        int year = Integer.parseInt(yearAndMonth.substring(0, 4));
        int month = Integer.parseInt(yearAndMonth.substring(5, 7));
        List<Lesson> lessons = service.findGroupMonthlyLessons(groupId, year, month);
        model.addAttribute("lessons", lessons);
        return "timetable/group-timetable-result";
    }
    
    @PostMapping(value = "/teacher-timetable-result", params = "date")
    public String viewTeacherTimetablePage(Model model, @RequestParam(name = "teacherId") int teacherId, @RequestParam(name = "date") Date date) {
        List<Lesson> lessons = service.findTeacherDailyLessons(teacherId, date);
        model.addAttribute("lessons", lessons);
        return "timetable/teacher-timetable-result";
    }
    
    @PostMapping(value = "/teacher-timetable-result", params = "month")
    public String viewTeacherTimetablePage(Model model, @RequestParam(name = "teacherId") int teacherId,  @RequestParam(name = "month") String yearAndMonth) {
        int year = Integer.parseInt(yearAndMonth.substring(0, 4));
        int month = Integer.parseInt(yearAndMonth.substring(5, 7));
        List<Lesson> lessons = service.findTeacherMonthlyLessons(teacherId, year, month);
        model.addAttribute("lessons", lessons);
        return "timetable/teacher-timetable-result";
    }
    
}
