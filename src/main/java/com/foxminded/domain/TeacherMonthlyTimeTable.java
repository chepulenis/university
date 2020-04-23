package com.foxminded.domain;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((monthlyTimeTable == null) ? 0 : monthlyTimeTable.hashCode());
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
        TeacherMonthlyTimeTable other = (TeacherMonthlyTimeTable) obj;
        if (monthlyTimeTable == null) {
            if (other.monthlyTimeTable != null)
                return false;
        } else if (!monthlyTimeTable.equals(other.monthlyTimeTable))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TeacherMonthlyTimeTable [monthlyTimeTable=" + monthlyTimeTable + "]";
    }
    
}
