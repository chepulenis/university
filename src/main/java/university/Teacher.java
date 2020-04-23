package university;

import java.util.List;

public class Teacher extends Person{
    
    private List<Subject> subjects;

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

}
