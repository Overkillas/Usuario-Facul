package com.example.Usuario.Controller;

import com.example.Usuario.DTOs.UserDTO;
import com.example.Usuario.Entities.User;
import com.example.Usuario.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Usuario.Client.UserRestClient;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRestClient userRestClient;

    @GetMapping("/Client/{id}")
    public User getUser(@PathVariable Integer id) {
        return userRestClient.getUserById(id);
    }

    @PostMapping("/Client")
    public String addUser(@RequestBody User user) {
        return userRestClient.addUser(user);
    }

    @DeleteMapping("/Client/{id}")
    public String removeUserClient(@PathVariable Integer id) {
        return userRestClient.removeUser(id);
    }

    @PostMapping()
    public ResponseEntity<String> addUser(@RequestBody UserDTO user) {
        userService.AddUser(user);
        return ResponseEntity.ok("Usuário aceito.");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> removeUser(@PathVariable Integer id) {
        userService.removeUser(userService.getById(id).get());
        return ResponseEntity.ok("Usuário removido.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByEmail(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getById(id).orElse(null));
    }
}
