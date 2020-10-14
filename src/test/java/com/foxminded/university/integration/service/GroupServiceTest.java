package com.foxminded.university.integration.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.foxminded.university.domain.Group;
import com.foxminded.university.integration.Util;
import com.foxminded.university.repository.GroupRepository;
import com.foxminded.university.service.GroupService;

@RunWith(SpringRunner.class)
public class GroupServiceTest {

    @TestConfiguration
    static class GroupServiceTestContextConfiguration {
        @Bean
        public GroupService groupService() {
            return new GroupService();
        }
    }

    @Autowired
    private GroupService service;

    @MockBean
    private GroupRepository repository;

    @Before
    public void setUp() {
        Group group1 = new Group(1, "af-12");
        Group group2 = new Group(2, "bz-24");
        Group group3 = new Group(3, "ax-32");
        List<Group> groups = Arrays.asList(group1, group2, group3);

        Mockito.when(repository.findById(group1.getId())).thenReturn(Optional.of(group1));
        Mockito.when(repository.findById(group2.getId())).thenReturn(Optional.of(group2));
        Mockito.when(repository.findById(group3.getId())).thenReturn(Optional.of(group3));
        Mockito.when(repository.findAll()).thenReturn(groups);
        Mockito.when(repository.findById(0)).thenReturn(Optional.empty());
    }

    @Test
    public void whenInvalidIdthenGroupShouldNotBeFound() {
        Group group = service.findGroupById(0);
        assertThat(group).isNull();
        Util.verifyFindByIdIsCalledOnce(repository);
    }

    @Test
    public void whenValidIdthenGroupShouldBeFound() {
        Group group = service.findGroupById(1);
        assertThat(group.getId()).isEqualByComparingTo(1);
        Util.verifyFindByIdIsCalledOnce(repository);
    }

    @Test
    public void given3Groups_whengetAll_thenReturn3Records() {
        Group group1 = new Group(1, "af-12");
        Group group2 = new Group(2, "bz-24");
        Group group3 = new Group(3, "ax-32");
        List<Group> groups = service.findAllGroups();
        Util.verifyFindAllIsCalledOnce(repository);
        assertThat(groups).hasSize(3).extracting(Group::getId).contains(group1.getId(), group2.getId(), group3.getId());
        assertThat(groups).hasSize(3).extracting(Group::getName).contains(group1.getName(), group2.getName(),
                group3.getName());

    }
    
}
