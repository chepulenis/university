package com.foxminded.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TeacherMonthlyTimetable extends DailyTimetable{
    
    private int year;
    private int month;
    private Date date;
    
    
    public TeacherMonthlyTimetable() {
        
    }
    
    public TeacherMonthlyTimetable(int id, List<Lesson> lessons, int year, int month) {
        this.year = year;
        this.month = month;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        this.date = calendar.getTime();
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Date getDate() {
        return date;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + month;
        result = prime * result + year;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        TeacherMonthlyTimetable other = (TeacherMonthlyTimetable) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (month != other.month)
            return false;
        if (year != other.year)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TeacherMonthlyTimetable [year=" + year + ", month=" + month + ", date=" + date + "]";
    }
    
    

}
