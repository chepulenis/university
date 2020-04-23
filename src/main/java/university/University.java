package university;

import java.util.List;

public class University {
    private String name;
    private List<Group> groups;
    private List<Teacher> teachers;
    
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
}
