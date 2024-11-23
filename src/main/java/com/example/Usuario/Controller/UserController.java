package com.example.Usuario.Controller;

import com.example.Usuario.DTOs.UserDTO;
import com.example.Usuario.Entities.User;
import com.example.Usuario.Repositories.UserRepository;
import com.example.Usuario.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService userService;

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
