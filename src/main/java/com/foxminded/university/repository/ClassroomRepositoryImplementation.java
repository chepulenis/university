package com.foxminded.university.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.university.domain.Classroom;

@Component
@Transactional
public class ClassroomRepositoryImplementation {
    
    @Autowired
    private ClassroomRepository repository;

    public Classroom findClassroomById(int id) {
        return repository.findById(id).get();
    }

    public List<Classroom> findAllClassrooms() {
        return repository.findAll();
    }

    public void deleteClassroom(int id) {
        repository.deleteById(id);
    }

    public void updateClassroom(Classroom classroom) {
        repository.save(classroom);
    }

    public void createClassroom(Classroom classroom) {
        repository.save(classroom);
    }
}
