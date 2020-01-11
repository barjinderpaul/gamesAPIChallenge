package com.challenge.gameapi.controllers;

import com.challenge.gameapi.model.User;
import com.challenge.gameapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;


    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User adduser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email){

        Long userId = userService.saveUser(username, password, email);
        return userService.getUserById(userId);
    }

}
