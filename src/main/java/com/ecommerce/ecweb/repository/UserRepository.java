package com.ecommerce.ecweb.repository;

import com.ecommerce.ecweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{
    Optional<User> findByUserEmail(String userEmail);
    Boolean existsByUserEmail(String userEmail);

}
