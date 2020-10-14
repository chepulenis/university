package com.foxminded.university.integration.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.foxminded.university.domain.Group;
import com.foxminded.university.repository.GroupRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GroupRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GroupRepository repository;

    @Test
    public void whenFindByIdThenReturnGroup() {
        Group expected = new Group(1, "xx-33");
        entityManager.persistAndFlush(expected);

        Group actual = repository.findById(1).get();
        assertEquals(expected, actual);
    }

    @Test
    public void whenInvalidIdThenReturnNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            repository.findById(0).get();
        });
    }
    
    @Test
    public void givenListOfGroupsWhenFindAllThenReturnAllGroups() {
        Group group1 = new Group(1, "af-23");
        Group group2 = new Group(2, "bs-13");
        Group group3 = new Group(3, "sz-41");
        List<Group> expectedGroups = Arrays.asList(group1, group2, group3);
        
        entityManager.persist(group1);
        entityManager.persist(group2);
        entityManager.persist(group3);
        entityManager.flush();
        
        List<Group> actualGroups = repository.findAll();
        
        assertEquals(expectedGroups, actualGroups);
    }

}
