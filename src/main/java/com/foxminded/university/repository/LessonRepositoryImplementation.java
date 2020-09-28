package com.foxminded.university.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lesson;
import com.foxminded.university.domain.Subject;
import com.foxminded.university.domain.Teacher;
import com.foxminded.university.mapper.LessonMapper;

@Component
@Transactional
public class LessonRepositoryImplementation {

    private static final String SQL_LESSONS_JOIN = "select lessons.id as lesson_id, classrooms.id as classroom_id, classrooms.name as classroom_name, classrooms.size as classroom_size, "
            + "teachers.id as teacher_id, teachers.first_name as teacher_first_name, teachers.last_name as teacher_last_name, teachers.age as teacher_age,"
            + " subjects.id as subject_id, subjects.name as subject_name, groups.id as group_id, groups.name as group_name, lessons.start_time "
            + "from lessons inner join classrooms on lessons.classroom_id = classrooms.id inner join teachers on lessons.teacher_id = teachers.id "
            + " inner join subjects on lessons.subject_id = subjects.id inner join groups on lessons.group_id = groups.id ";
    private static final String SQL_FIND_DAILY_LESSONS_FOR_GROUP = SQL_LESSONS_JOIN
            + "where lessons.group_id = ? and lessons.start_time between ?::timestamp and (?::timestamp + interval '23:59' hour)";
    private static final String SQL_FIND_MONTHLY_LESSONS_FOR_GROUP = SQL_LESSONS_JOIN
            + "where lessons.group_id = ? and extract (year from lessons.start_time) = ? and extract (month from lessons.start_time) = ?";
    private static final String SQL_FIND_DAILY_LESSONS_FOR_TEACHER = SQL_LESSONS_JOIN
            + "where lessons.teacher_id = ? and lessons.start_time between ?::timestamp and (?::timestamp + interval '23:59' hour)";
    private static final String SQL_FIND_MONTHLY_LESSONS_FOR_TEACHER = SQL_LESSONS_JOIN
            + "where lessons.teacher_id = ? and extract (year from lessons.start_time) = ? and extract (month from lessons.start_time) = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LessonRepositoryImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Autowired
    private LessonRepository repository;

    public Lesson findLessonById(int id) {
        return repository.findById(id).get();
    }

    public Classroom findClassroom(Lesson lesson) {
        return repository.findById(lesson.getId()).get().getClassroom();
    }

    public Teacher findTeacher(Lesson lesson) {
        return repository.findById(lesson.getId()).get().getTeacher();
    }

    public Subject findSubject(Lesson lesson) {
        return repository.findById(lesson.getId()).get().getSubject();
    }

    public Group findGroup(Lesson lesson) {
        return repository.findById(lesson.getId()).get().getGroup();
    }

    public List<Lesson> findAllLessons() {
        return repository.findAll();
    }

    public void createLesson(Lesson lesson) {
        repository.save(lesson);
    }

    public void updateLesson(Lesson lesson) {
        repository.save(lesson);
    }

    public void deleteLesson(int id) {
        repository.deleteById(id);
    }

    public List<Lesson> findGroupDailyLessons(int groupId, Date date) {
        return jdbcTemplate.query(SQL_FIND_DAILY_LESSONS_FOR_GROUP, new LessonMapper(), groupId, date, date);
    }

    public List<Lesson> findGroupMonthlyLessons(int groupId, int year, int month) {
        return jdbcTemplate.query(SQL_FIND_MONTHLY_LESSONS_FOR_GROUP, new LessonMapper(), groupId, year, month);
    }
    
    public List<Lesson> findTeacherDailyLessons(int teacherId, Date date) {
        return jdbcTemplate.query(SQL_FIND_DAILY_LESSONS_FOR_TEACHER, new LessonMapper(), teacherId, date, date);
    }

    public List<Lesson> findTeacherMonthlyLessons(int teacherId, int year, int month) {
        return jdbcTemplate.query(SQL_FIND_MONTHLY_LESSONS_FOR_TEACHER, new LessonMapper(), teacherId, year, month);
    }


}
