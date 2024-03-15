package com.example.booknook.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart_info")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cid;
    private String bfk="";
}
