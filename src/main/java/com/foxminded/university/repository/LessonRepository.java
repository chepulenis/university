package com.foxminded.university.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foxminded.university.domain.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    public static final String SQL_FIND_DAILY_LESSONS_FOR_GROUP = "select * from lessons where group_id = :groupId and start_time between cast(:date as timestamp) and (cast(:date as timestamp) + interval '23:59' hour)";
    public static final String SQL_FIND_MONTHLY_LESSONS_FOR_GROUP = "select * from lessons where group_id = :groupId and extract (year from lessons.start_time) = :year and extract (month from lessons.start_time) = :month";
    public static final String SQL_FIND_DAILY_LESSONS_FOR_TEACHER = "select * from lessons where teacher_id = :teacherId and start_time between cast(:date as timestamp) and (cast(:date as timestamp) + interval '23:59' hour)";
    public static final String SQL_FIND_MONTHLY_LESSONS_FOR_TEACHER = "select * from lessons where teacher_id = :teacherId and extract (year from lessons.start_time) = :year and extract (month from lessons.start_time) = :month";

    @Query(value = SQL_FIND_DAILY_LESSONS_FOR_GROUP, nativeQuery = true)
    public List<Lesson> findGroupDailyLessons(@Param("groupId") int groupId, @Param("date") Date date);

    @Query(value = SQL_FIND_MONTHLY_LESSONS_FOR_GROUP, nativeQuery = true)
    public List<Lesson> findGroupMonthlyLessons(@Param("groupId") int groupId, @Param("year") int year,
            @Param("month") int month);

    @Query(value = SQL_FIND_DAILY_LESSONS_FOR_TEACHER, nativeQuery = true)
    public List<Lesson> findTeacherDailyLessons(@Param("teacherId") int teacherId, @Param("date") Date date);

    @Query(value = SQL_FIND_MONTHLY_LESSONS_FOR_TEACHER, nativeQuery = true)
    public List<Lesson> findTeacherMonthlyLessons(@Param("teacherId") int teacherId, @Param("year") int year,
            @Param("month") int month);

}
