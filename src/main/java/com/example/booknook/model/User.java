package com.example.booknook.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name="user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String password;

    @OneToOne
    @JoinColumn(name = "cart_fk")
    private Cart cart;
}
