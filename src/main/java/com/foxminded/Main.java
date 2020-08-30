package com.foxminded;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.domain.Classroom;
import com.foxminded.domain.Group;
import com.foxminded.domain.GroupDailyTimetable;
import com.foxminded.domain.GroupMonthlyTimetable;
import com.foxminded.domain.Lesson;
import com.foxminded.domain.Student;
import com.foxminded.domain.Subject;
import com.foxminded.domain.Teacher;
import com.foxminded.domain.TeacherDailyTimetable;
import com.foxminded.domain.TeacherMonthlyTimetable;
import com.foxminded.service.ClassroomService;
import com.foxminded.config.SpringJdbcConfig;
import com.foxminded.dao.GroupDao;
import com.foxminded.dao.GroupDailyTimetableDao;
import com.foxminded.dao.GroupMonthlyTimetableDao;
import com.foxminded.dao.LessonDao;
import com.foxminded.dao.StudentDao;
import com.foxminded.dao.SubjectDao;
import com.foxminded.dao.TeacherDao;
import com.foxminded.dao.TeacherDailyTimetableDao;
import com.foxminded.dao.TeacherMonthlyTimetableDao;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws ParseException {
        SpringApplication.run(Main.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);

        TeacherDao teacherDao = context.getBean(TeacherDao.class);
        SubjectDao subjectDao = context.getBean(SubjectDao.class);

        for (Subject subject : subjectDao.findAllSubjects()) {
            System.out.println(subject);
        }

        System.out.println("List of teachers is:");

        for (Teacher teacher : teacherDao.findAllTeachers()) {
            System.out.println(teacher);
        }

        System.out.println("\nGet teacher with ID 2");

        Teacher teacherById = teacherDao.findTeacherById(2);
        System.out.println(teacherById);

        System.out.println("\nCreating teacher: ");

        Teacher t = new Teacher(5, "Alexandra", "Clarkson", 43);
        System.out.println(t);
        teacherDao.createTeacher(t);
        System.out.println("\nList of teacher is:");

        for (Teacher teacher : teacherDao.findAllTeachers()) {
            System.out.println(teacher);
        }

        System.out.println("\nUpdate person with ID 4");

        Teacher tteacher = teacherDao.findTeacherById(1);
        tteacher.setLastName("Williamson");
        teacherDao.updateTeacher(tteacher);

        System.out.println("\nList of teachers:");
        for (Teacher teacher : teacherDao.findAllTeachers()) {
            System.out.println(teacher);
        }

        System.out.println("List of subjects for teacher " + tteacher.getLastName());
        System.out.println(teacherDao.findTeacherSubjects(tteacher));

        Subject subject = subjectDao.findSubjectById(4);

        System.out.println("List of teachers for subject " + subject.getName());
        System.out.println(subjectDao.findSubjectTeachers(subject));

        StudentDao studentDao = context.getBean(StudentDao.class);
        Student student = studentDao.findStudentById(3);
        System.out.println(student);
        System.out.println(student.getFirstName() + " " + student.getLastName() + "'s group is: "
                + studentDao.findStudentGroup(student));

        GroupDao groupDao = context.getBean(GroupDao.class);
        Group group = groupDao.findGroupById(4);
        System.out.println(groupDao.findStudentsGroup(group));

        LessonDao lessonDao = context.getBean(LessonDao.class);
        Lesson lesson = lessonDao.findLessonById(2);
        System.out.println(lesson);

        System.out.println("Classroom: " + lessonDao.findClassroom(lesson));
        System.out.println("Teacher: " + lessonDao.findTeacher(lesson));
        System.out.println("Subject: " + lessonDao.findSubject(lesson));
        System.out.println("Group: " + lessonDao.findGroup(lesson));
        System.out.println("Final: " + lesson);

        TeacherDailyTimetableDao teacherDailyTimetableDao = context.getBean(TeacherDailyTimetableDao.class);
        TeacherDailyTimetable teacherDailyTimetable = teacherDailyTimetableDao.findTeacherTimetableById(1);
        System.out.println(teacherDailyTimetable);
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date day = df.parse("2020/09/03");
        List<Lesson> lessons = teacherDailyTimetableDao.findDailyLessons(teacherDailyTimetable, 1,
                new java.sql.Date(day.getTime()));
        System.out.println("-------------");

        for (Lesson teacherLesson : lessons) {
            lessonDao.findClassroom(teacherLesson);
            lessonDao.findTeacher(teacherLesson);
            lessonDao.findGroup(teacherLesson);
            lessonDao.findSubject(teacherLesson);
            System.out.println(teacherLesson + "\n");

        }

        GroupDailyTimetableDao groupDailyTimetableDao = context.getBean(GroupDailyTimetableDao.class);
        GroupDailyTimetable groupDailyTimetable = groupDailyTimetableDao.findGroupTimetableById(1);
        System.out.println(groupDailyTimetable);
        Date day2 = df.parse("2020/09/03");
        List<Lesson> lessonsForGroups = groupDailyTimetableDao.findDailyLessons(groupDailyTimetable, 1,
                new java.sql.Date(day2.getTime()));
        System.out.println("Group daily timetable: ");

        for (Lesson groupLesson : lessonsForGroups) {
            lessonDao.findClassroom(groupLesson);
            lessonDao.findTeacher(groupLesson);
            lessonDao.findGroup(groupLesson);
            lessonDao.findSubject(groupLesson);
            System.out.println(groupLesson + "\n");
        }

        TeacherMonthlyTimetableDao teacherMonthlyTimetableDao = context.getBean(TeacherMonthlyTimetableDao.class);
        TeacherMonthlyTimetable teacherMonthlyTimetable = teacherMonthlyTimetableDao.findTeacherTimetableById(1);
        System.out.println("Teacher monthly time table:\n"
                + teacherMonthlyTimetableDao.findMonthlyLessons(teacherMonthlyTimetable, 1, 2020, 9));

        GroupMonthlyTimetableDao groupMonthlyTimetableDao = context.getBean(GroupMonthlyTimetableDao.class);
        GroupMonthlyTimetable groupMonthlyTimetable = groupMonthlyTimetableDao.findGroupTimetableById(1);
        System.out.println("Group monthly time table:\n"
                + groupMonthlyTimetableDao.findMonthlyLessons(groupMonthlyTimetable, 1, 2020, 9));

        ClassroomService classroomService = context.getBean(ClassroomService.class);
        classroomService.updateClassroom(new Classroom(2, "Class of Arts", 33));

        context.close();
    }
}
