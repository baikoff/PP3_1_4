package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    public void createUser(User user);

    public User readUser(Long id);

    public User updateUser(User user);

    public void deleteUser(Long id);

    public User getUserByName(String name);

    public List<User> allUsers();

}
