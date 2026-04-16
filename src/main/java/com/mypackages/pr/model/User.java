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