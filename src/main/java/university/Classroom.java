package university;

public class Classroom {

    private String name;
    private int size;
    
    public boolean isFull(int studentsAmount) {
        return size == studentsAmount;
    }
    
    public String getName() {
        return name;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setSize(int size) {
        this.size = size;
    } 
    
}
