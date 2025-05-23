package com.project.taskwebapp.taskapp.services;

import com.project.taskwebapp.taskapp.models.User;
import com.project.taskwebapp.taskapp.exceptions.EmailException;
import com.project.taskwebapp.taskapp.exceptions.NotFoundException;
import com.project.taskwebapp.taskapp.repository.UserRepository;
import com.project.taskwebapp.taskapp.utils.interfaces.AuthServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements AuthServiceInterface {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User with id: " + id + " not found"));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new EmailException("Email does not exist"));
    }
}
