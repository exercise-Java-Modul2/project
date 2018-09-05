package com.codegym.controller;

import com.codegym.model.Role;
import com.codegym.model.User;
import com.codegym.service.roles.RoleService;
import com.codegym.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("roles")
    public Iterable<Role> listRoles() {
        return roleService.findAll();
    }

    @GetMapping("/users")
    public ModelAndView listUser(@RequestParam("s")Optional<String> s) {
        Iterable<User> users;
        if (s.isPresent()) {
            users = (Iterable<User>) userService.findUserByName(s.get());
        } else {
            users = userService.findAll();
        }

        ModelAndView modelAndView = new ModelAndView("user/list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/users/create")
    public ModelAndView showCreateForm() {

        User user = new User();

        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/users/create")
    public ModelAndView createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("user", user);
        modelAndView.addObject("message", "User created compliment");

        return modelAndView;
    }

    @GetMapping("/users/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/user/edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/users/update")
    public ModelAndView updateUser(@ModelAttribute("user") User user) {
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/user/edit");
        modelAndView.addObject("user", user);
        modelAndView.addObject("message", "User updated successfully");
        return modelAndView;
    }

    @GetMapping("/users/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/user/delete");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/users/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.remove(user.getId());

        return "redirect:/users";
    }

    @GetMapping("/users/detail/{id}")
    public ModelAndView detailUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/user/detail");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
