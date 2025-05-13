package com.example.calc2_fx.service;

import com.example.calc2_fx.model.User;
import com.example.calc2_fx.repository.UserRepository;

public class AuthService {

    private static User currentUser;
    private UserRepository userRepository = new UserRepository();

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return user;
        }
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public boolean register(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            return false;
        }
        User newUser = new User(username, password, "ROLE_USER");
        return userRepository.save(newUser);
    }

}
