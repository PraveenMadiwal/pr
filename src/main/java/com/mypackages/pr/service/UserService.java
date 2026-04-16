package com.mypackages.pr.service;

import com.mypackages.pr.model.User;
import com.mypackages.pr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // service layer
public class UserService {

    @Autowired
    private UserRepository repo; // connect repository

    public String register(User user) {

        User oldUser = repo.findByEmail(user.getEmail()); // check email exists

        if (oldUser != null) {
            return "Email already exists";
        }

        repo.save(user); // save into DB

        return "Signup Successful";
    }

    public String login(User user) {

        User dbUser = repo.findByEmail(user.getEmail()); // get user by email

        if (dbUser == null) {
            return "User not found";
        }

        if (dbUser.getPassword().equals(user.getPassword())) {
            return "Login Successful";
        }

        return "Wrong Password";
    }
}