package com.ecommerce.ecweb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Audited(targetAuditMode = NOT_AUDITED)
@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id

    private UUID ID=UUID.randomUUID();
    private String userEmail;
    private String FirstName;
    private String Lname;
    @Pattern(regexp ="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String Password;
    private boolean isDeleted;
    private boolean isArchive;
    private boolean isExpired;
    private boolean isLocked;
    private int fAttempts;    //failed/invalid attempt counts
    private Date pwdUpdDate;   //passsword update date


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Role> roles;
//
//    @OneToOne(mappedBy = "user")
//    private Seller seller;
//    @OneToOne(mappedBy = "user")
//    private Customer customer;


}
