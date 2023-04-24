package com.ecommerce.ecweb.repository;

import com.ecommerce.ecweb.entity.Customer;
import com.ecommerce.ecweb.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {

}
