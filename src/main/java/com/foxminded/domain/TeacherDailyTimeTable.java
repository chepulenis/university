package com.foxminded.domain;

import java.util.Date;
import java.util.List;

public class TeacherDailyTimeTable extends DailyTimeTable{

    public TeacherDailyTimeTable(List<Lesson> lessons, Date date) {
        super(lessons, date);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
