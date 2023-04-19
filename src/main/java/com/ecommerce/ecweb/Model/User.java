package com.ecommerce.ecweb.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID ID;
    private String userEmail;
    private String FirstName;
    private String Lname;
    private String Password;
    private boolean isDeleted;
    private boolean isArchive;
    private boolean isExpired;
    private boolean isLocked;
    private int fAttempts;    //failed/invalid attempt counts
    private Date pwdUpdDate;   //passsword update date

    @ManyToMany
    private List<Role> roles;

    @OneToOne(mappedBy = "user")
    private Seller seller;
    @OneToOne(mappedBy = "user")
    private Customer customer;


}
