package com.ecommerce.ecweb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ID;

    private String authority;

    @ManyToMany(mappedBy = "roles")
    private List<User> user;
}
