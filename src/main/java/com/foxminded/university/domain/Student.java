package com.foxminded.university.domain;

public class Student extends Person{

    private Group group;

    public Student() {
        
    }
    
    public Student(int id, String firstName, String lastName, int age, Group group) {
        super(id, firstName, lastName, age);
        this.group = group;
    }
    
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
        return "Student [id=" + super.getId() + ", frist_name=" + super.getFirstName() + ", last_name=" + super.getLastName() + " [group=" + group + "]";
    }


}
