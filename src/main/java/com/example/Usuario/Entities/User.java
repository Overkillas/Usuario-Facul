package com.example.Usuario.Entities;

import com.example.Usuario.DTOs.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "User")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "adress")
    String address;

    @Column(name = "adress2")
    String address2;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    public User(UserDTO data) {
        this.name = data.name();
        this.address = data.address();
        this.address2 = data.address2();
        this.email = data.email();
        this.password = data.password();
    }

}
