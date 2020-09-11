package com.foxminded.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.domain.Lesson;
import com.foxminded.service.LessonService;

@Controller
public class LessonController {
    
    @Autowired
    private LessonService service;

    @RequestMapping("/lessons")
    public String viewLessonsPage(Model model) {
        List<Lesson> lessons = service.findAllLessons();
        model.addAttribute("lessons", lessons);
        return "lesson/lessons";
    }

    @RequestMapping("/new_lesson")
    public String showNewForm(Model model) {
        Lesson lesson = new Lesson();
        model.addAttribute("lesson", lesson);
        return "lesson/new_lesson";
    }

    @RequestMapping(value = "/lesson_save", method = RequestMethod.POST)
    public String save(@ModelAttribute("lesson") Lesson lesson) {
        service.createLesson(lesson);
        return "redirect:/lessons";
    }

    @RequestMapping("/lesson_edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("lesson/lesson_edit");
        Lesson lesson = service.findLessonById(id);
        mav.addObject("lesson", lesson);
        return mav;
    }

    @RequestMapping(value = "/lesson_update", method = RequestMethod.POST)
    public String update(@ModelAttribute("lesson") Lesson lesson) {
        service.updateLesson(lesson);
        return "redirect:/lessons";
    }

    @RequestMapping("/lesson_delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        service.deleteLesson(id);
        return "redirect:/lessons";
    }
    
    @RequestMapping("/group_timetable")
    public String viewGroupDailyTimetablePage() {
        return "timetable/group_timetable";
    }
    
    @RequestMapping("/teacher_timetable")
    public String viewTeacherDailyTimetablePage() {
        return "timetable/teacher_timetable";
    }
    
    @RequestMapping(value = "/group_timetable_result", params = "date", method = RequestMethod.POST)
    public String viewGroupTimetablePage(Model model, @RequestParam(name = "groupId") int groupId, @RequestParam(name = "date") Date date) {
        List<Lesson> lessons = service.findGroupDailyLessons(groupId, date);
        model.addAttribute("lessons", lessons);
        return "timetable/group_timetable_result";
    }
    
    @RequestMapping(value = "/group_timetable_result", params = "month", method = RequestMethod.POST)
    public String viewGroupTimetablePage(Model model, @RequestParam(name = "groupId") int groupId,  @RequestParam(name = "month") String yearAndMonth) {
        int year = Integer.parseInt(yearAndMonth.substring(0, 4));
        int month = Integer.parseInt(yearAndMonth.substring(5, 7));
        List<Lesson> lessons = service.findGroupMonthlyLessons(groupId, year, month);
        model.addAttribute("lessons", lessons);
        return "timetable/group_timetable_result";
    }
    
    @RequestMapping(value = "/teacher_timetable_result", params = "date", method = RequestMethod.POST)
    public String viewTeacherTimetablePage(Model model, @RequestParam(name = "teacherId") int teacherId, @RequestParam(name = "date") Date date) {
        List<Lesson> lessons = service.findTeacherDailyLessons(teacherId, date);
        model.addAttribute("lessons", lessons);
        return "timetable/teacher_timetable_result";
    }
    
    @RequestMapping(value = "/teacher_timetable_result", params = "month", method = RequestMethod.POST)
    public String viewTeacherTimetablePage(Model model, @RequestParam(name = "teacherId") int teacherId,  @RequestParam(name = "month") String yearAndMonth) {
        int year = Integer.parseInt(yearAndMonth.substring(0, 4));
        int month = Integer.parseInt(yearAndMonth.substring(5, 7));
        List<Lesson> lessons = service.findTeacherMonthlyLessons(teacherId, year, month);
        model.addAttribute("lessons", lessons);
        return "timetable/teacher_timetable_result";
    }
    
}
