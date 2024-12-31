package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
   private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> list = userService.getAllUsers();
        System.out.println(list.size());
        return list;
    }
}
