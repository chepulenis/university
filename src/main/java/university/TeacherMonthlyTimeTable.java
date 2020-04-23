package university;

import java.util.ArrayList;
import java.util.List;

public class TeacherMonthlyTimeTable {
    
    List<TeacherDailyTimeTable> monthlyTimeTable;

    public TeacherMonthlyTimeTable() {
        this.monthlyTimeTable = new ArrayList<TeacherDailyTimeTable>();
    }

    public List<TeacherDailyTimeTable> getMonthlyTimeTable() {
        return monthlyTimeTable;
    }

    public void setMonthlyTimeTable(List<TeacherDailyTimeTable> monthlyTimeTable) {
        this.monthlyTimeTable = monthlyTimeTable;
    }
    
}
