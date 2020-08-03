package com.foxminded;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.domain.Group;
import com.foxminded.domain.GroupDailyTimetable;
import com.foxminded.domain.GroupMonthlyTimetable;
import com.foxminded.domain.Lesson;
import com.foxminded.domain.Student;
import com.foxminded.domain.Subject;
import com.foxminded.domain.Teacher;
import com.foxminded.domain.TeacherDailyTimetable;
import com.foxminded.domain.TeacherMonthlyTimetable;
import com.foxminded.config.SpringJdbcConfig;
import com.foxminded.dao.GroupDAO;
import com.foxminded.dao.GroupDailyTimetableDAO;
import com.foxminded.dao.GroupMonthlyTimetableDAO;
import com.foxminded.dao.LessonDAO;
import com.foxminded.dao.StudentDAO;
import com.foxminded.dao.SubjectDAO;
import com.foxminded.dao.TeacherDAO;
import com.foxminded.dao.TeacherDailyTimetableDAO;
import com.foxminded.dao.TeacherMonthlyTimetableDAO;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws ParseException {
        SpringApplication.run(Main.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);

        TeacherDAO teacherDAO = context.getBean(TeacherDAO.class);
        SubjectDAO subjectDAO = context.getBean(SubjectDAO.class);

        for (Subject subject : subjectDAO.findAllSubjects()) {
            System.out.println(subject);
        }

        System.out.println("List of teachers is:");

        for (Teacher teacher : teacherDAO.findAllTeachers()) {
            System.out.println(teacher);
        }

        System.out.println("\nGet teacher with ID 2");

        Teacher teacherById = teacherDAO.findTeacherById(2);
        System.out.println(teacherById);

        System.out.println("\nCreating teacher: ");

        Teacher t = new Teacher(5, "Alexandra", "Clarkson", 43);
        System.out.println(t);
        teacherDAO.createTeacher(t);
        System.out.println("\nList of teacher is:");

        for (Teacher teacher : teacherDAO.findAllTeachers()) {
            System.out.println(teacher);
        }

        System.out.println("\nUpdate person with ID 4");

        Teacher tteacher = teacherDAO.findTeacherById(1);
        tteacher.setLastName("Williamson");
        teacherDAO.updateTeacher(tteacher);

        System.out.println("\nList of teachers:");
        for (Teacher teacher : teacherDAO.findAllTeachers()) {
            System.out.println(teacher);
        }

        System.out.println("List of subjects for teacher " + tteacher.getLastName());
        System.out.println(teacherDAO.findTeacherSubjects(tteacher));

        Subject subject = subjectDAO.findSubjectById(4);

        System.out.println("List of teachers for subject " + subject.getName());
        System.out.println(subjectDAO.findSubjectTeachers(subject));

        StudentDAO studentDAO = context.getBean(StudentDAO.class);
        Student student = studentDAO.findStudentById(3);
        System.out.println(student);
        System.out.println(student.getFirstName() + " " + student.getLastName() + "'s group is: "
                + studentDAO.findStudentGroup(student));

        GroupDAO groupDAO = context.getBean(GroupDAO.class);
        Group group = groupDAO.findGroupById(4);
        System.out.println(groupDAO.findStudentsGroup(group));

        LessonDAO lessonDAO = context.getBean(LessonDAO.class);
        Lesson lesson = lessonDAO.findLessonById(2);
        System.out.println(lesson);

        System.out.println("Classroom: " + lessonDAO.findClassroom(lesson));
        System.out.println("Teacher: " + lessonDAO.findTeacher(lesson));
        System.out.println("Subject: " + lessonDAO.findSubject(lesson));
        System.out.println("Group: " + lessonDAO.findGroup(lesson));
        System.out.println("Final: " + lesson);

        TeacherDailyTimetableDAO teacherDailyTimetableDAO = context.getBean(TeacherDailyTimetableDAO.class);
        TeacherDailyTimetable teacherDailyTimetable = teacherDailyTimetableDAO.findTeacherTimetableById(1);
        System.out.println(teacherDailyTimetable);
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date day = df.parse("2020/09/03");
        List<Lesson> lessons = teacherDailyTimetableDAO.findDailyLessons(teacherDailyTimetable, 1,
                new java.sql.Date(day.getTime()));
        System.out.println("-------------");

        for (Lesson teacherLesson : lessons) {
            lessonDAO.findClassroom(teacherLesson);
            lessonDAO.findTeacher(teacherLesson);
            lessonDAO.findGroup(teacherLesson);
            lessonDAO.findSubject(teacherLesson);
            System.out.println(teacherLesson + "\n");

        }

        GroupDailyTimetableDAO groupDailyTimetableDAO = context.getBean(GroupDailyTimetableDAO.class);
        GroupDailyTimetable groupDailyTimetable = groupDailyTimetableDAO.findGroupTimetableById(1);
        System.out.println(groupDailyTimetable);
        Date day2 = df.parse("2020/09/03");
        List<Lesson> lessonsForGroups = groupDailyTimetableDAO.findDailyLessons(groupDailyTimetable, 1,
                new java.sql.Date(day2.getTime()));
        System.out.println("Group daily timetable: ");

        for (Lesson groupLesson : lessonsForGroups) {
            lessonDAO.findClassroom(groupLesson);
            lessonDAO.findTeacher(groupLesson);
            lessonDAO.findGroup(groupLesson);
            lessonDAO.findSubject(groupLesson);
            System.out.println(groupLesson + "\n");
        }

        TeacherMonthlyTimetableDAO teacherMonthlyTimetableDAO = context.getBean(TeacherMonthlyTimetableDAO.class);
        TeacherMonthlyTimetable teacherMonthlyTimetable = teacherMonthlyTimetableDAO.findTeacherTimetableById(1);
        System.out.println("Teacher monthly time table:\n" + teacherMonthlyTimetableDAO.findMonthlyLessons(teacherMonthlyTimetable, 1, 2020, 9));
        
        
        GroupMonthlyTimetableDAO groupMonthlyTimetableDAO = context.getBean(GroupMonthlyTimetableDAO.class);
        GroupMonthlyTimetable groupMonthlyTimetable = groupMonthlyTimetableDAO.findGroupTimetableById(1);
        System.out.println("Group monthly time table:\n" + groupMonthlyTimetableDAO.findMonthlyLessons(groupMonthlyTimetable, 1, 2020, 9));
        
        context.close();
    }
}
