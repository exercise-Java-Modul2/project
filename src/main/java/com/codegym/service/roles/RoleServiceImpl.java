package com.codegym.service.roles;

import com.codegym.model.Role;
import com.codegym.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public Iterable<Role> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return rolesRepository.findOne(id);
    }

    @Override
    public void save(Role role) {
        rolesRepository.save(role);
    }

    @Override
    public void remove(Long id) {
        rolesRepository.delete(id);
    }
}
