package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class AdminController {

    private final UserServiceImpl service;

    public AdminController(UserServiceImpl service) {
        this.service = service;
    }


    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        return "/admin/admin_panel";
    }

    @GetMapping("/index/{id}")
    public String show(@PathVariable("id") Long id, Model model, Principal principal) {
        return "/index";
    }

    @GetMapping("/index")
    public void userInfo(Principal principal, Model model) {
    }

    @GetMapping("/admin/admin_new")
    public String newUser(Model model) {
        return "/admin/admin_panel";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("user") User user) {
        return "redirect:/admin/admin_panel";
    }

    @GetMapping("/index/{id}/edit")
    public String updateUser(Model model, @PathVariable("id") Long id) {
        return "/admin/admin_panel";
    }

    @PatchMapping("/index/update")
    public String update(@ModelAttribute("user") User newUser) {
        return "redirect:/admin/admin_panel";
    }

    @DeleteMapping("/index/delete")
    public String delete(@ModelAttribute("user") User user) {
        return "redirect:/admin/admin_panel";
    }
}