package com.example.Usuario.Controller;

import com.example.Usuario.Client.OrderClient;
import com.example.Usuario.DTOs.UserDTO;
import com.example.Usuario.Entities.User;
import com.example.Usuario.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    OrderClient orderClient;

    @PostMapping("/order")
    public String makeOrder(@RequestBody Map<String, Object> order){
        return orderClient.addOrder(order);
    }

    @GetMapping("/order")
    public List<Map<String, Object>> getOrders(){
        return orderClient.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public List<Map<String, Object>> getOrder(@PathVariable int id){
        return orderClient.getOrderByUser(id);
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
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getById(id).orElse(null));
    }
}