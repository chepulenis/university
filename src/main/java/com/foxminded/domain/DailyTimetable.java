package com.foxminded.domain;

import java.util.Date;
import java.util.List;

public class DailyTimetable {
    
    private int id;
    private List<Lesson> lessons;
    private Date date;
    
    public DailyTimetable() {
    }
    
    public DailyTimetable(int id, List<Lesson> lessons, Date date) {
        this.id = id;
        this.lessons = lessons;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + id;
        result = prime * result + ((lessons == null) ? 0 : lessons.hashCode());
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
        DailyTimetable other = (DailyTimetable) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (id != other.id)
            return false;
        if (lessons == null) {
            if (other.lessons != null)
                return false;
        } else if (!lessons.equals(other.lessons))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DailyTimeTable [id=" + id + ", lessons=" + lessons + ", date=" + date + "]";
    }

}
