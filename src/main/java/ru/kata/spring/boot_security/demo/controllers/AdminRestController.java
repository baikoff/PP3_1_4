package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/")
public class AdminRestController {

    private final UserServiceImpl service;

    private final PasswordEncoder passwordEncoder;

    public AdminRestController(UserServiceImpl service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(service.allUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.createUser(user);
        return new ResponseEntity<>(service.getUserByName(user.getName()), HttpStatus.OK);
    }

    @PatchMapping("/users")
    public ResponseEntity<User> changeUser(@RequestBody User user) {
        service.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
