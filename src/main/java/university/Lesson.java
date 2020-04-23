package university;

import java.util.Date;

public class Lesson {
    
    private Classroom classroom;
    private TeacherSubject teacherSubject;
    private Group group;
    private Date date;
    
    public Lesson(Classroom classroom, TeacherSubject teacherSubject, Group group, Date date) {
        this.classroom = classroom;
        this.teacherSubject = teacherSubject;
        this.group = group;
        this.date = date;
    }
    
    public Classroom getClassroom() {
        return classroom;
    }
    
    public TeacherSubject getTeacherSubject() {
        return teacherSubject;
    }
    
    public Group getGroup() {
        return group;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
    
    public void setTeacherSubject(TeacherSubject teacherSubject) {
        this.teacherSubject = teacherSubject;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
        
}
