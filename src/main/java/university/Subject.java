package university;

import java.util.List;

public class Subject {
    
    private String name;
    private List<Teacher> teachers;
    
    public Subject(String name, List<Teacher> teachers) {
        this.name = name;
        this.teachers = teachers;
    }

    public String getName() {
        return name;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
    
}
