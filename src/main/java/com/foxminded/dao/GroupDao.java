package com.foxminded.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.domain.Group;
import com.foxminded.domain.Student;
import com.foxminded.mapper.GroupMapper;
import com.foxminded.mapper.StudentMapper;

@Component
public class GroupDao {

    private final String SQL_FIND_GROUP = "select * from groups where id = ?";
    private final String SQL_DELETE_GROUP = "delete from groups where id = ?";
    private final String SQL_UPDATE_GROUP = "update groups set name = ? where id = ?";
    private final String SQL_GET_ALL_GROUPS = "select * from groups order by id";
    private final String SQL_INSERT_GROUP = "insert into groups(id, name) values(?,?)";
    private final String SQL_GET_STUDENTS_GROUP = "select * from students inner join groups "
            + "on students.group_id = groups.id where group_id = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Group findGroupById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_GROUP, new Object[] { id }, new GroupMapper());
    }

    public List<Group> findAllGroups() {
        return jdbcTemplate.query(SQL_GET_ALL_GROUPS, new GroupMapper());
    }

    public boolean deleteGroup(int id) {
        return jdbcTemplate.update(SQL_DELETE_GROUP, id) > 0;
    }

    public boolean updateGroup(Group group) {
        return jdbcTemplate.update(SQL_UPDATE_GROUP, group.getName(), group.getId()) > 0;
    }

    public boolean createGroup(Group group) {
        return jdbcTemplate.update(SQL_INSERT_GROUP, group.getId(), group.getName()) > 0;
    }
    
    public List<Student> findStudentsGroup(Group group){
        group.setStudents(jdbcTemplate.query(SQL_GET_STUDENTS_GROUP, new StudentMapper(), group.getId()));
        return group.getStudents();
    }

}
