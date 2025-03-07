package org.ghost.sprintcloud.msvc_courses.domain.repositories;

import org.ghost.sprintcloud.msvc_courses.domain.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface ICourseRepository extends CrudRepository<Course, Long>{
    
}
