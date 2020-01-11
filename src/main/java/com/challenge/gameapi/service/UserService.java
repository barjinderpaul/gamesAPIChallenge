package com.challenge.gameapi.service;

import com.challenge.gameapi.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    List<User> getAllUsers();

    Long saveUser(String username, String password, String email);
}
