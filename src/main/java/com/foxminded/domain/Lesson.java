package com.foxminded.domain;

import java.sql.Timestamp;

public class Lesson {
    
    private int id;
    private Classroom classroom;
    private Teacher teacher;
    private Subject subject;
    private Group group;
    private Timestamp timestamp;
    
    public Lesson(int id, Classroom classroom, Teacher teacher, Subject subject, Group group, Timestamp timestamp) {
        this.id = id;
        this.classroom = classroom;
        this.teacher = teacher;
        this.subject = subject;
        this.group = group;
        this.timestamp = timestamp;
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
    
    public Timestamp getTimestamp() {
        return timestamp;
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
    
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classroom == null) ? 0 : classroom.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
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
                + ", group=" + group + ", timestamp=" + timestamp + "]";
    }



}
