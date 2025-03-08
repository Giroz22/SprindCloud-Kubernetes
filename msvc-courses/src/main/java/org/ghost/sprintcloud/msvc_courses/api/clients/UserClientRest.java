package org.ghost.sprintcloud.msvc_courses.api.clients;

import org.ghost.sprintcloud.msvc_courses.domain.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-users", url = "localhost:8001")
public interface UserClientRest {
    @GetMapping("/{id}")
    User getUser(@PathVariable Long id);

    @PostMapping
    User createUser(@RequestBody User user);
}
