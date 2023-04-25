package com.ecommerce.ecweb.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Seller extends User{

    private String GST;
    private long comp_no;
    private String comp_name;


    @OneToOne(mappedBy = "seller",cascade = CascadeType.ALL)
    private Address address;
}
