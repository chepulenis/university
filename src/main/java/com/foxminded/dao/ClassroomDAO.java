package com.foxminded.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.domain.Classroom;
import com.foxminded.mapper.ClassroomMapper;

@Component
public class ClassroomDAO {
    
    private final String SQL_FIND_CLASSROOM = "select * from clasrooms where id = ?";
    private final String SQL_DELETE_CLASSROOM = "delete from clasrooms where id = ?";
    private final String SQL_UPDATE_CLASSROOM = "update clasrooms set name = ?, size  = ? where id = ?";
    private final String SQL_FIND_ALL_CLASSROOMS = "select * from clasrooms";
    private final String SQL_INSERT_CLASSROOM = "insert into clasrooms(id, name, size) values(?,?,?)";
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ClassroomDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Classroom findClassroomById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_CLASSROOM, new Object[] { id }, new ClassroomMapper());
    }

    public List<Classroom> findAllClassrooms() {
        return jdbcTemplate.query(SQL_FIND_ALL_CLASSROOMS, new ClassroomMapper());
    }

    public boolean deleteClassrom(Classroom classroom) {
        return jdbcTemplate.update(SQL_DELETE_CLASSROOM, classroom.getId()) > 0;
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
