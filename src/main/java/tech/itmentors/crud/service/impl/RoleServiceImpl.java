package tech.itmentors.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.itmentors.crud.exception.NotFoundException;
import tech.itmentors.crud.model.Role;
import tech.itmentors.crud.repository.RoleRepository;
import tech.itmentors.crud.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) throw new NotFoundException("Not Found: Roles is not found");
        return roles;
    }

}
