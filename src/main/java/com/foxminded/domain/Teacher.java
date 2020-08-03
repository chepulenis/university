package com.foxminded.domain;

public class Teacher extends Person{
    
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
        return "Teacher [id=" + super.getId() + ", frist_name=" + super.getFirstName() + ", last_name=" + super.getLastName() + "]";
    }

    
}
