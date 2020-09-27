package com.foxminded.university.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {

    @ManyToMany
    @JoinTable(name = "teachers_subjects", joinColumns = @JoinColumn(name = "teachers_id"), inverseJoinColumns = @JoinColumn(name = "subjects_id"))
    Set<Subject> teacherSubjects;

    public Teacher() {
    }

    public Teacher(int id, String firstName, String lastName, int age) {
        super(id, firstName, lastName, age);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Teacher [id=" + super.getId() + ", first_name=" + super.getFirstName() + ", last_name="
                + super.getLastName() + "]";
    }

}
