package com.foxminded.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.domain.Classroom;
import com.foxminded.domain.Group;
import com.foxminded.domain.Lesson;
import com.foxminded.domain.Subject;
import com.foxminded.domain.Teacher;
import com.foxminded.mapper.ClassroomMapper;
import com.foxminded.mapper.GroupMapper;
import com.foxminded.mapper.LessonMapper;
import com.foxminded.mapper.SubjectMapper;
import com.foxminded.mapper.TeacherMapper;

@Component
public class LessonDao {

    private static final String SQL_FIND_LESSON_CLASSROOM = "select * from classrooms inner join lessons "
            + "on classrooms.id = lessons.classroom_id where lessons.id = ?";
    private static final String SQL_FIND_LESSON_TEACHER = "select * from teachers inner join lessons "
            + "on teachers.id = lessons.teacher_id where lessons.id = ?";
    private static final String SQL_FIND_LESSON_SUBJECT = "select * from subjects inner join lessons "
            + "on subjects.id = lessons.subject_id where lessons.id = ?";
    private static final String SQL_FIND_LESSON_GROUP = "select * from groups inner join lessons "
            + "on groups.id = lessons.group_id where lessons.id = ?";

    private static final String SQL_LESSONS_JOIN = "select lessons.id as lesson_id, classrooms.id as classroom_id, classrooms.name as classroom_name, classrooms.size as classroom_size, "
            + "teachers.id as teacher_id, teachers.first_name as teacher_first_name, teachers.last_name as teacher_last_name, teachers.age as teacher_age,"
            + " subjects.id as subject_id, subjects.name as subject_name, groups.id as group_id, groups.name as group_name, lessons.start_time "
            + "from lessons inner join classrooms on lessons.classroom_id = classrooms.id inner join teachers on lessons.teacher_id = teachers.id "
            + " inner join subjects on lessons.subject_id = subjects.id inner join groups on lessons.group_id = groups.id ";

    private static final String SQL_FIND_ALL_LESSONS = SQL_LESSONS_JOIN + "order by lessons.id";
    private static final String SQL_FIND_LESSON = SQL_LESSONS_JOIN + "where lessons.id = ?";
    private static final String SQL_INSERT_LESSON = "insert into lessons(id, classroom_id, teacher_id, subject_id, group_id, start_time) values(?,?,?,?,?,?)";
    private static final String SQL_UPDATE_LESSON = "update lessons set classroom_id = ?, teacher_id= ?, subject_id = ?, group_id= ?, start_time = ? where id = ?";
    private static final String SQL_DELETE_LESSON = "delete from lessons where id = ?";
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
    public LessonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Lesson findLessonById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_LESSON, new Object[] { id }, new LessonMapper());
    }

    public Classroom findClassroom(Lesson lesson) {
        lesson.setClassroom(
                jdbcTemplate.queryForObject(SQL_FIND_LESSON_CLASSROOM, new ClassroomMapper(), lesson.getId()));
        return lesson.getClassroom();
    }

    public Teacher findTeacher(Lesson lesson) {
        lesson.setTeacher(jdbcTemplate.queryForObject(SQL_FIND_LESSON_TEACHER, new TeacherMapper(), lesson.getId()));
        return lesson.getTeacher();
    }

    public Subject findSubject(Lesson lesson) {
        lesson.setSubject(jdbcTemplate.queryForObject(SQL_FIND_LESSON_SUBJECT, new SubjectMapper(), lesson.getId()));
        return lesson.getSubject();
    }

    public Group findGroup(Lesson lesson) {
        lesson.setGroup(jdbcTemplate.queryForObject(SQL_FIND_LESSON_GROUP, new GroupMapper(), lesson.getId()));
        return lesson.getGroup();
    }

    public List<Lesson> findAllLessons() {
        return jdbcTemplate.query(SQL_FIND_ALL_LESSONS, new LessonMapper());
    }

    public boolean createLesson(Lesson lesson) {
        return jdbcTemplate.update(SQL_INSERT_LESSON, lesson.getId(), lesson.getClassroom().getId(),
                lesson.getTeacher().getId(), lesson.getSubject().getId(), lesson.getGroup().getId(),
                lesson.getStartTime()) > 0;
    }

    public boolean updateLesson(Lesson lesson) {
        return jdbcTemplate.update(SQL_UPDATE_LESSON, lesson.getClassroom().getId(), lesson.getTeacher().getId(),
                lesson.getSubject().getId(), lesson.getGroup().getId(), lesson.getStartTime(), lesson.getId()) > 0;
    }

    public boolean deleteLesson(int id) {
        return jdbcTemplate.update(SQL_DELETE_LESSON, id) > 0;
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
