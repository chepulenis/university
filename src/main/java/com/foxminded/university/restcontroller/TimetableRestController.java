package com.foxminded.university.restcontroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.university.domain.Lesson;
import com.foxminded.university.service.LessonService;

@RestController
public class TimetableRestController {

    @Autowired
    private LessonService service;

    @GetMapping(value = "/timetable/groups/daily", produces = "application/json")
    public List<Lesson> getGroupDailyTimetable(@RequestParam(name = "groupId") int groupId,
            @RequestParam(name = "date") Date date) {
        return service.findTeacherDailyLessons(groupId, date);
    }

    @GetMapping(value = "/timetable/groups/monthly", produces = "application/json")
    public List<Lesson> getGroupMonthlyTimetable(@RequestParam(name = "groupId") int groupId,
            @RequestParam(name = "month") String yearAndMonth) {
        int year = Integer.parseInt(yearAndMonth.substring(0, 4));
        int month = Integer.parseInt(yearAndMonth.substring(5, 7));
        return service.findGroupMonthlyLessons(groupId, year, month);
    }

    @GetMapping(value = "/timetable/teachers/daily", produces = "application/json")
    public List<Lesson> viewTeacherTimetablePage(@RequestParam(name = "teacherId") int teacherId,
            @RequestParam(name = "date") Date date) {
        return service.findTeacherDailyLessons(teacherId, date);
    }

    @GetMapping(value = "/timetable/teachers/monthly", produces = "application/json")
    public List<Lesson> viewTeacherTimetablePage(@RequestParam(name = "teacherId") int teacherId,
            @RequestParam(name = "month") String yearAndMonth) {
        int year = Integer.parseInt(yearAndMonth.substring(0, 4));
        int month = Integer.parseInt(yearAndMonth.substring(5, 7));
        return service.findTeacherMonthlyLessons(teacherId, year, month);
    }

}
