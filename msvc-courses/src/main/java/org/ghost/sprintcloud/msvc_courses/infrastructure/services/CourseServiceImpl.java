package org.ghost.sprintcloud.msvc_courses.infrastructure.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.ghost.sprintcloud.msvc_courses.api.clients.UserClientRest;
import org.ghost.sprintcloud.msvc_courses.domain.entities.Course;
import org.ghost.sprintcloud.msvc_courses.domain.entities.CourseUser;
import org.ghost.sprintcloud.msvc_courses.domain.models.User;
import org.ghost.sprintcloud.msvc_courses.domain.repositories.ICourseRepository;
import org.ghost.sprintcloud.msvc_courses.infrastructure.abstract_services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl implements ICourseService{

    @Autowired
    private ICourseRepository repository;

    @Autowired
    private UserClientRest client;

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

    @Override
    @Transactional
    public Optional<User> setUsuario(User user, Long courseId) {
        Optional<Course> courseOptional = this.repository.findById(courseId);
        if (courseOptional.isPresent()) {
            User userMsvc = client.getUser(user.getId());

            Course course = courseOptional.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userMsvc.getId());
            
            course.addCourseUser(courseUser);

            this.repository.save(course);
            return Optional.of(userMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user, Long courseId) {
        Optional<Course> courseOptional = this.repository.findById(courseId);
        if (courseOptional.isPresent()) {
            User newUserMsvc = client.createUser(user);
            Course course = courseOptional.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(newUserMsvc.getId());
            
            course.addCourseUser(courseUser);
            repository.save(course);
            return Optional.of(newUserMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> deleteUser(User user, Long courseId) {
        Optional<Course> courseOptional = this.repository.findById(courseId);
        if (courseOptional.isPresent()) {
            User userMsvc = client.getUser(user.getId());
            Course course = courseOptional.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userMsvc.getId());
            
            course.removeCourseUser(courseUser);
            repository.save(course);
            return Optional.of(userMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> getCourseWithUsers(Long courseId) {
        Optional<Course> courseOptional = this.repository.findById(courseId);
        if(courseOptional.isPresent()) {
            Course course = courseOptional.get();

            if(!course.getCourseUsers().isEmpty()){
                List<Long> ids = course.getCourseUsers().stream()
                .map(cu -> cu.getUserId())
                .collect(Collectors.toList());

                List<User> users = this.client.getUsersByIds(ids);
                course.setUsers(users);

            }

            return Optional.of(course);
        }

        return Optional.empty();
    }
    
}
