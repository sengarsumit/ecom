package com.ecommerce.ecweb.Model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
@Entity 
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ID;
    @Enumerated(EnumType.STRING)
    private Roles Authority;

    @ManyToMany(mappedBy = "roles")
    private List<User> user;
}
