package com.ecommerce.ecweb.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Address {
    @Id
    private UUID addId;
    private String city;
    private String state;
    private String country;
    private String AddresLine;
    private int zipcode;
    private String label;
    @OneToOne
    private Seller seller;

//    @ManyToOne
//    private Customer customer;
}
