package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    User readUser(Long id);

    User updateUser(User user);

    void deleteUser(Long id);

    User getUserByName(String name);

    List<User> allUsers();

}
