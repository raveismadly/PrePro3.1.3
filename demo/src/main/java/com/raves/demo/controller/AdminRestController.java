package com.raves.demo.controller;


import com.raves.demo.model.User;
import com.raves.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminRestController {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/admin");
        return modelAndView;
    }

    @GetMapping("/rest")
    public List<User> allUsersPage() {
        return service.allUsers();
    }

    @PostMapping("/add/rest")
    public void addUser(@RequestBody User user) {
        User user1 = new User(user.getUsername(), user.getPassword());
        Long role = user.getRoles().iterator().next().getId();
        service.setRole(user1, role);
        service.addUser(user1);
    }

    @GetMapping("/edit/rest/{id}")
    public User getUserByID(@PathVariable Integer id) {
        return service.getUserById(id);
    }

    @PostMapping("/edit/rest")
    public void updateUser(@RequestBody User user) {
        User user1 = new User(user.getId(), user.getUsername(), user.getPassword());
        Long role = user.getRoles().iterator().next().getId();
        service.setRole(user1, role);
        service.updateUser(user1);
    }

    @GetMapping("/delete/rest/{id}")
    public void deleteUserByID(@PathVariable("id") Integer id) {
        User user = service.getUserById(id);
        service.deleteUser(user);
    }
}
