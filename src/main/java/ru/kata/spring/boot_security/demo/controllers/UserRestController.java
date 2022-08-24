package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;


@RestController
@RequestMapping("/")
public class UserRestController {

    private final UserServiceImpl service;

    public UserRestController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.readUser(id), HttpStatus.OK);
    }

}
