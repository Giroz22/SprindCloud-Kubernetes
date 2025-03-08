package org.ghost.springcloud.msvc.users.infrastructure.abstract_services;

import java.util.List;
import java.util.Optional;

import org.ghost.springcloud.msvc.users.domain.entities.User;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void deleteById(Long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
