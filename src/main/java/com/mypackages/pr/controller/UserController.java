package com.mypackages.pr.controller; // package name

import com.mypackages.pr.model.User; // import User model
import com.mypackages.pr.service.UserService; // import service
import org.springframework.beans.factory.annotation.Autowired; // auto inject object
import org.springframework.web.bind.annotation.*; // REST annotations

@RestController // tells spring this is API controller
@RequestMapping("/api") // common API path
@CrossOrigin(origins = "*") // allow frontend calls
public class UserController {

    @Autowired // create object automatically
    private UserService service;

    @PostMapping("/signup") // POST api/signup
    public String signup(@RequestBody User user) { // receive JSON user data
        return service.register(user); // call register method
    }

    @PostMapping("/login") // POST api/login
    public String login(@RequestBody User user) { // receive login data
        return service.login(user); // call login method
    }
}