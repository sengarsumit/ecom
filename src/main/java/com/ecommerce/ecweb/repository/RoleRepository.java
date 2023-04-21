package com.ecommerce.ecweb.repository;

import com.ecommerce.ecweb.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByAuthority(String authority);

    void deleteByAuthority(String authority);
}
