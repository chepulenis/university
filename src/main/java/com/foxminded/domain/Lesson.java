package com.foxminded.domain;

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
    
    public Lesson() {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classroom == null) ? 0 : classroom.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + ((teacherSubject == null) ? 0 : teacherSubject.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lesson other = (Lesson) obj;
        if (classroom == null) {
            if (other.classroom != null)
                return false;
        } else if (!classroom.equals(other.classroom))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        if (teacherSubject == null) {
            if (other.teacherSubject != null)
                return false;
        } else if (!teacherSubject.equals(other.teacherSubject))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Lesson [classroom=" + classroom + ", teacherSubject=" + teacherSubject + ", group=" + group + ", date="
                + date + "]";
    }
    
}
