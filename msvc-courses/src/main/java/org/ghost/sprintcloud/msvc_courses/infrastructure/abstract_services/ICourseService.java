package org.ghost.sprintcloud.msvc_courses.infrastructure.abstract_services;

import java.util.List;
import java.util.Optional;

import org.ghost.sprintcloud.msvc_courses.domain.entities.Course;

public interface ICourseService {
    List<Course> getAll();
    Optional<Course> getById(Long id);
    Course save(Course course);
    void delete(Long id);
}
