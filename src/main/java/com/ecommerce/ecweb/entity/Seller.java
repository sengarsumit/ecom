package com.ecommerce.ecweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Seller extends User{

    private String GST;
    private int comp_no;
    private String comp_name;

    @OneToOne
    private User user;

    @OneToOne(mappedBy = "seller")
    private Address address;
}
