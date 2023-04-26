package com.ecomapp.customer.repository;

import com.ecomapp.customer.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long > {
    Role findByRolename(String name);
}
