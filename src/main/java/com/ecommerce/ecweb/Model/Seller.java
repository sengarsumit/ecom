package com.ecommerce.ecweb.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
