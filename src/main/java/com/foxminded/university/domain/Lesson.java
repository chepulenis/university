package com.foxminded.university.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="lessons")
public class Lesson {
    
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classroom classroom;
    @OneToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;
    @OneToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;
    @OneToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startTime;
    
    public Lesson(int id, Classroom classroom, Teacher teacher, Subject subject, Group group, Date startTime) {
        this.id = id;
        this.classroom = classroom;
        this.teacher = teacher;
        this.subject = subject;
        this.group = group;
        this.startTime = startTime;
        
    }
    
    public Lesson() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Classroom getClassroom() {
        return classroom;
    }
    
    public Teacher getTeacher() {
        return teacher;
    }
    
    public Subject getSubject() {
        return subject;
    }
    
    public Group getGroup() {
        return group;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classroom == null) ? 0 : classroom.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + id;
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        if (id != other.id)
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        if (teacher == null) {
            if (other.teacher != null)
                return false;
        } else if (!teacher.equals(other.teacher))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Lesson [id=" + id + ", classroom=" + classroom + ", teacher=" + teacher + ", subject=" + subject
                + ", group=" + group + ", timestamp=" + startTime + "]";
    }



}
