package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;

import javax.transaction.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    public UserDetailServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ru.kata.spring.boot_security.demo.model.User user = userDAO.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        } else {
            return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
        }


    }
}