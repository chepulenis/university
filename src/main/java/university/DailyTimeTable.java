package university;

import java.util.Date;
import java.util.List;

public class DailyTimeTable {
    
    private List<Lesson> lessons;
    private Date date;
    
    public DailyTimeTable(List<Lesson> lessons, Date date) {
        this.lessons = lessons;
        this.date = date;
    }
    
    public List<Lesson> getLessons() {
        return lessons;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
}
