package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

   private final RoleDAO dao;

    public RoleServiceImpl(RoleDAO dao) {
        this.dao = dao;
    }

    @Override
    public Set<Role> saveRoles(Long index) {
        Set<Role> rolesSet = new HashSet<>();
        if (index == 1) {
            rolesSet.add(dao.getById((long) 1));
        } else if (index == 2) {
            rolesSet.add(dao.getById((long) 2));
        } else if (index == 3) {
            rolesSet.addAll(dao.findAll());
        }
        return rolesSet;
    }
}