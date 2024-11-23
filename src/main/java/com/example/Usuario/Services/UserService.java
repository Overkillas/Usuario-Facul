package com.example.Usuario.Services;

import com.example.Usuario.DTOs.UserDTO;
import com.example.Usuario.Entities.User;
import com.example.Usuario.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User AddUser(UserDTO userData) {
        return userRepository.save(new User(userData));
    }

    public String removeUser(User user) {
        userRepository.delete(user);
        return "User deleted";
    }

    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }
}
