package com.ecommerce.ecweb.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Customer extends User {
    private int contact;
    @OneToOne
    private User user;
//    @OneToMany
//    private List<Address> address;

}
