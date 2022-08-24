package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserDAO userDao;

    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void createUser(User user) {
        userDao.save(user);
    }

    @Transactional
    public User readUser(Long id) {
        return userDao.findById(id).get();
    }

    @Transactional
    public User updateUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    @Transactional
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Transactional
    public List<User> allUsers() {
        return userDao.findAll();
    }

}

