package org.ghost.sprintcloud.msvc_courses.infrastructure.services;

import java.util.List;
import java.util.Optional;

import org.ghost.sprintcloud.msvc_courses.domain.entities.Course;
import org.ghost.sprintcloud.msvc_courses.domain.repositories.ICourseRepository;
import org.ghost.sprintcloud.msvc_courses.infrastructure.abstract_services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl implements ICourseService{

    @Autowired
    private ICourseRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Course> getAll() {
        return (List<Course>) this.repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> getById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return this.repository.save(course);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
    
}
