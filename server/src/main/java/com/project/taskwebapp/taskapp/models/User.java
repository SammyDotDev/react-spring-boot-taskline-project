package com.project.taskwebapp.taskapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull(message = "username must not be null")
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(
            mappedBy = "user"
    )
    @JsonManagedReference("user-projects")
    private final List<Project> projects = new ArrayList<>();


    @OneToMany(
            mappedBy = "user"
    )
    @JsonManagedReference("user-tasks")
    private final List<Task> tasks = new ArrayList<>();
    public User() {
    }

    public User(Integer id, String username, String email, String password, List<Project> projects, List<Task> tasks) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
