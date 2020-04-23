package com.foxminded.domain;

import java.util.List;

public class University {
    private String name;
    private List<Group> groups;
    private List<Teacher> teachers;
    
    public University(){
    }
    
    public University(String name, List<Group> groups, List<Teacher> teachers) {
        this.name = name;
        this.groups = groups;
        this.teachers = teachers;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }
    
    public void removeGroup(Group group) {
        groups.remove(group);
    }
    
    public Group editGroup(Group group) {
        return group;
    }
    
    public Group getGroup(Group group) {
        return group;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Group> getGroups() {
        return groups;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((groups == null) ? 0 : groups.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((teachers == null) ? 0 : teachers.hashCode());
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
        University other = (University) obj;
        if (groups == null) {
            if (other.groups != null)
                return false;
        } else if (!groups.equals(other.groups))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (teachers == null) {
            if (other.teachers != null)
                return false;
        } else if (!teachers.equals(other.teachers))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "University [name=" + name + ", groups=" + groups + ", teachers=" + teachers + "]";
    }    
    
    
}
