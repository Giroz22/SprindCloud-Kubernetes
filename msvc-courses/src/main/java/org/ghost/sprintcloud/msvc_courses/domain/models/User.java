package org.ghost.sprintcloud.msvc_courses.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;

    private String name;
   
    private String email;

    private String password;
}
