package org.ghost.springcloud.msvc.users.api.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.ghost.springcloud.msvc.users.domain.entities.User;
import org.ghost.springcloud.msvc.users.infrastructure.abstract_services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {
    @Autowired
    private IUserService service;

    @GetMapping
    public List<User> getAll() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<User> userOptional = this.service.findById(id);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } 

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        
        if (result.hasErrors()) {
            return validar(result);
        }

        if(this.service.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Message", "The email is already in use"));
        }

        user.setId(null);
        User UserSaved = this.service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserSaved);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody User user, BindingResult result) {
        
        if (result.hasErrors()) {
            return validar(result);
        }        

        Optional<User> userOptional = this.service.findById(id);

        if (userOptional.isPresent()) {
            User userDB = userOptional.get();

            if(!user.getEmail().equalsIgnoreCase(userDB.getEmail()) && this.service.findByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("Message", "The email is already in use"));
            }

            userDB.setName(user.getName());
            userDB.setEmail(user.getEmail());
            userDB.setPassword(user.getPassword());
                        
            return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(userDB));
        } 

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<User> userOptional = this.service.findById(id);
        
        if (userOptional.isPresent()) {
            this.service.deleteById(id);
            return ResponseEntity.noContent().build();                
        }

        return ResponseEntity.notFound().build();
        
    }

    @GetMapping("/users-by-ids")
    private ResponseEntity<?> getAllUserByIds(@RequestParam List<Long> ids ){
        return ResponseEntity.ok(this.service.findAllByIds(ids));
    }

    private ResponseEntity<?> validar(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "The field: " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
