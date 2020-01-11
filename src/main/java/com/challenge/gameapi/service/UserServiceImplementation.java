package com.challenge.gameapi.service;

import com.challenge.gameapi.exception.InvalidArgumentException;
import com.challenge.gameapi.model.User;
import com.challenge.gameapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private void isValidUser(String username, String email, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            throw new InvalidArgumentException("User with username: " + username + " already exists");
        }
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail != null) {
            throw new InvalidArgumentException("User with email : " + email + " already exists");
        }
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Long saveUser(String username, String password, String email) {
        isValidUser(username, email, password);

        User user = new User();

        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEmail(email);

        userRepository.save(user);

        Long userId = userRepository.save(user).getId();
        return userId;
    }

}
