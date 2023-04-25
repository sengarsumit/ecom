package com.ecommerce.ecweb.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Customer extends User {
    private long contact;

    @OneToMany(mappedBy = "add_cust",cascade = CascadeType.ALL)
    private List<Address> c_add;


}
