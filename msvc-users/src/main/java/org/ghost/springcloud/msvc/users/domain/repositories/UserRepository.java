package org.ghost.springcloud.msvc.users.domain.repositories;

import org.ghost.springcloud.msvc.users.domain.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email); 

    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> findUserEmail(String email); 

    boolean existsByEmail(String email);
}
