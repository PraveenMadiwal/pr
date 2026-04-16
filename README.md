# pr
src/main/resources/application.properties

# Run backend on port 8080
server.port=8080

# Use H2 memory database
spring.datasource.url=jdbc:h2:mem:loginapp

# Driver for H2
spring.datasource.driverClassName=org.h2.Driver

# Default username
spring.datasource.username=sa

# No password
spring.datasource.password=

# Auto create/update tables
spring.jpa.hibernate.ddl-auto=update

# Show SQL in console
spring.jpa.show-sql=true

# Enable H2 browser console
spring.h2.console.enabled=true

# H2 console path
spring.h2.console.path=/h2-console


conntroller/UserController.java
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


service/UserService.java
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

model/User.java
package com.mypackages.pr.model;

import jakarta.persistence.*;

@Entity // this class becomes DB table
@Table(name="users") // table name users
public class User {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email; // column
    private String password; // column

    public User() {} // empty constructor

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}



