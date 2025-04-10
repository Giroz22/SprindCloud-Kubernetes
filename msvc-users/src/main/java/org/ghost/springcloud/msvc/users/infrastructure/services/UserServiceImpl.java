package org.ghost.springcloud.msvc.users.infrastructure.services;

import java.util.List;
import java.util.Optional;

import org.ghost.springcloud.msvc.users.api.client.CourseClientRest;
import org.ghost.springcloud.msvc.users.domain.entities.User;
import org.ghost.springcloud.msvc.users.domain.repositories.UserRepository;
import org.ghost.springcloud.msvc.users.infrastructure.abstract_services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private CourseClientRest client;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) this.repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return this.repository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.repository.deleteById(id);
        this.client.deleteCourseUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        // Optional<User> user = this.repository.findByEmail(email);
        Optional<User> user = this.repository.findUserEmail(email);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
       return this.repository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllByIds(Iterable<Long> ids) {
        return (List<User>) this.repository.findAllById(ids);
    }
    
}
