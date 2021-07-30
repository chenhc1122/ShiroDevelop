package com.yqz.shiromybatis.controller;


import com.yqz.shiromybatis.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddController {
    @Autowired
        private UserServiceImpl userService;
    @RequestMapping("/addRole")
    public String addUser(@RequestParam("username") String username, @RequestParam("role") String role ){

        System.out.println(username+role);
        userService.addRole(username, role);
        return "index";

    }

}
