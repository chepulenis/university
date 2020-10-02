package com.foxminded.university.restcontroller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.university.domain.Lesson;
import com.foxminded.university.service.LessonService;

@RestController
public class LessonRestController {

    @Autowired
    private LessonService service;

    @GetMapping(value = "/lessons", produces = "application/json")
    public List<Lesson> getLessons() {
        List<Lesson> lessons = service.findAllLessons();
        return lessons;
    }

    @GetMapping(value = "/lessons/{id}", produces = "application/json")
    public Lesson findLessonById(@PathVariable int id) {
        return service.findLessonById(id);
    }

    @PostMapping(value = "/lessons", consumes = "application/json", produces = "application/json")
    public Lesson addLesson(@RequestBody Lesson lesson) {
        return service.createLesson(lesson);
    }

    @PutMapping(value = "/lessons/{id}", consumes = "application/json", produces = "application/json")
    public Lesson updateLesson(@RequestBody Lesson lesson) {
        return service.updateLesson(lesson);
    }

    @DeleteMapping(value = "lessons/{id}", produces = "application/json")
    public void deleteLesson(@PathVariable int id) {
        service.deleteLesson(id);
    }

    @PostMapping(value = "/group-timetable-result", params = "date", consumes = "application/json", produces = "application/json")
    public List<Lesson> viewGroupTimetablePage(Model model, @RequestParam(name = "groupId") int groupId,
            @RequestParam(name = "date") Date date) {
        List<Lesson> lessons = service.findGroupDailyLessons(groupId, date);
        return lessons;
    }
    
    @PostMapping(value = "/group-timetable-result", params = "month", consumes = "application/json", produces = "application/json")
    public List<Lesson> viewGroupTimetablePage(Model model, @RequestParam(name = "groupId") int groupId,  @RequestParam(name = "month") String yearAndMonth) {
        int year = Integer.parseInt(yearAndMonth.substring(0, 4));
        int month = Integer.parseInt(yearAndMonth.substring(5, 7));
        List<Lesson> lessons = service.findGroupMonthlyLessons(groupId, year, month);
        return lessons;
    }
    
    @PostMapping(value = "/teacher-timetable-result", params = "date", consumes = "application/json", produces = "application/json")
    public List<Lesson> viewTeacherTimetablePage(Model model, @RequestParam(name = "teacherId") int teacherId, @RequestParam(name = "date") Date date) {
        List<Lesson> lessons = service.findTeacherDailyLessons(teacherId, date);
        return lessons;
    }
    
    @PostMapping(value = "/teacher-timetable-result", params = "month", consumes = "application/json", produces = "application/json")
    public List<Lesson> viewTeacherTimetablePage(Model model, @RequestParam(name = "teacherId") int teacherId,  @RequestParam(name = "month") String yearAndMonth) {
        int year = Integer.parseInt(yearAndMonth.substring(0, 4));
        int month = Integer.parseInt(yearAndMonth.substring(5, 7));
        List<Lesson> lessons = service.findTeacherMonthlyLessons(teacherId, year, month);
        return lessons;
    }

}
