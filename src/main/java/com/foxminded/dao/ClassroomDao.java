package com.foxminded.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.domain.Classroom;
import com.foxminded.mapper.ClassroomMapper;

@Component
public class ClassroomDao {
    
    private final String SQL_FIND_CLASSROOM = "select * from classrooms where id = ?";
    private final String SQL_DELETE_CLASSROOM = "delete from classrooms where id = ?";
    private final String SQL_UPDATE_CLASSROOM = "update classrooms set name = ?, size  = ? where id = ?";
    private final String SQL_FIND_ALL_CLASSROOMS = "select * from classrooms order by id";
    private final String SQL_INSERT_CLASSROOM = "insert into classrooms(id, name, size) values(?,?,?)";
    
    private JdbcTemplate jdbcTemplate;
  
    @Autowired
    public ClassroomDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Classroom findClassroomById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_CLASSROOM, new Object[] { id }, new ClassroomMapper());
    }

    public List<Classroom> findAllClassrooms() {
        return jdbcTemplate.query(SQL_FIND_ALL_CLASSROOMS, new ClassroomMapper());
    }

    public boolean deleteClassroom(int id) {
        return jdbcTemplate.update(SQL_DELETE_CLASSROOM, id) > 0;
    }

    public boolean updateClassroom(Classroom classroom) {
        return jdbcTemplate.update(SQL_UPDATE_CLASSROOM, classroom.getName(), classroom.getSize(),
                classroom.getId()) > 0;
    }

    public boolean createClassroom(Classroom classroom) {
        return jdbcTemplate.update(SQL_INSERT_CLASSROOM, classroom.getId(), classroom.getName(),
                classroom.getSize()) > 0;
    }
}
