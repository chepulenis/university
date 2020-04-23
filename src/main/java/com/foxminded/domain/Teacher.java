package com.foxminded.domain;

import java.util.List;

public class Teacher extends Person{
    
    private List<Subject> subjects;
    
    public Teacher() {
    }

    public Teacher(String firstName, String lastName, List<Subject> subjects) {
        super(firstName, lastName);
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teacher other = (Teacher) obj;
        if (subjects == null) {
            if (other.subjects != null)
                return false;
        } else if (!subjects.equals(other.subjects))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Teacher [subjects=" + subjects + "]";
    }
    
}
