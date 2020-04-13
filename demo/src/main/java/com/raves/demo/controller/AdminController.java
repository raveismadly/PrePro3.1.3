//package com.raves.demo.controller;
//
//import com.raves.demo.model.Role;
//import com.raves.demo.model.User;
//import com.raves.demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Controller
//@RequestMapping(value = "/admin")
//public class AdminController {
//    private UserService service;
//
//    @Autowired
//    public void setService(UserService service) {
//        this.service = service;
//    }
//
//    @GetMapping("")
//    public String adminPage(Model model) {
//        List<User> allUsers = service.allUsers();
//        model.addAttribute("allUsers",allUsers);
//        return "/admin/admin";
//    }
//
//    @GetMapping(value = "/add")
//    public String addUserPage(Model model) {
//        model.addAttribute(new User());
//        return "/admin/addUser";
//    }
//
//    @PostMapping(value = "/add")
//    public String addUser(@ModelAttribute("user") User user,@ModelAttribute("role") Role role) {
//        System.out.println(role.getRole());
//        service.setRole(user,role);
//        service.addUser(user);
//        return "redirect:/admin/";
//    }
//
////    @GetMapping(value = "/edit/{id}")
////    public String editUserPage(@PathVariable("id") int id, Model model) {
////        model.addAttribute("user", service.getUserById(id));
////        return "/admin/editUser";
////    }
//    @GetMapping(value = "/findUser")
//    @ResponseBody
//    public User findUser(Integer id){
//      return service.getUserById(id);
//    }
//
//    @PostMapping(value = "/edit")
//    public String editFilm(@ModelAttribute("user") User user,Role role) {
//        System.out.println(user.getPassword());
//        System.out.println(user.getUsername());
//        service.setRole(user,role);
//        service.updateUser(user);
//        return "redirect:/admin/";
//    }
//
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public String deleteUserPage(@PathVariable("id") int id) {
//        User user = service.getUserById(id);
//        service.deleteUser(user);
//        return "redirect:/admin/";
//    }
//}
