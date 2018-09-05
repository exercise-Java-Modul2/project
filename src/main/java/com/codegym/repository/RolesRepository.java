package com.codegym.repository;

import com.codegym.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Role, Long> {
    Role findByRoles(String roles);
}
