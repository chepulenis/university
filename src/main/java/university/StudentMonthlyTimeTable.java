package university;

import java.util.ArrayList;
import java.util.List;

public class StudentMonthlyTimeTable {
    List<StudentDailyTimeTable> monthlyTimeTable;

    public StudentMonthlyTimeTable() {
        this.monthlyTimeTable = new ArrayList<StudentDailyTimeTable>();
    }

    public List<StudentDailyTimeTable> getMonthlyTimeTable() {
        return monthlyTimeTable;
    }

    public void setMonthlyTimeTable(List<StudentDailyTimeTable> monthlyTimeTable) {
        this.monthlyTimeTable = monthlyTimeTable;
    }
    
    
}
