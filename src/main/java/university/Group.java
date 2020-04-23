package university;

import java.util.List;

public class Group {
    
    private String name; 
    private List<Student> students;
    
    public String getName() {
        return name;
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    
}
