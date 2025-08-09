package com.tnsif.ShoppingMallManagement_User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.NoResultException;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> list() {
        return service.listAll();
    }

    @PostMapping("/users")
    public void add(@RequestBody User user) {
        service.save(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            User user = service.get(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoResultException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User updatedUser) {
        try {
            User existing = service.get(id);
            existing.setUsername(updatedUser.getUsername());
            existing.setPassword(updatedUser.getPassword());
            existing.setRole(updatedUser.getRole());
            existing.setEmail(updatedUser.getEmail());
            service.update(existing);
            return new ResponseEntity<>(existing, HttpStatus.OK);
        } catch (NoResultException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}