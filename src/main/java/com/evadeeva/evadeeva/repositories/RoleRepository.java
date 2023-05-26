package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findById(long roleId);
}
