package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebTestController {

    @Autowired
    private UserService userService;

    @GetMapping("show")
    public String show(Model model) {
        User user = userService.GetUserById(1);
        model.addAttribute("member",user);
        return "show";
    }
}
