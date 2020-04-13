package com.raves.demo.controller;

import com.raves.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {


    private UserDetailsService userService;

    @Autowired
    public void setUserService(@Qualifier("userDetailsServiceImp") UserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "user")
    public String userPage(@ModelAttribute("user") User user, Model model) {//
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();//get logged in username
        model.addAttribute(userService.loadUserByUsername(name));
        System.out.println(name);
        return "/user/user";
    }
}