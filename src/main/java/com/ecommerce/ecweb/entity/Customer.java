package com.ecommerce.ecweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
public class Customer extends User {
    private long contact;
    @OneToOne
    private User user;
//    @OneToMany
//    private List<Address> address;

    @OneToMany(mappedBy = "add_cust")
    private Collection<Address> c_add;


}
