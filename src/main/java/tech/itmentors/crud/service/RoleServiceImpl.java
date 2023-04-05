package tech.itmentors.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.itmentors.crud.dao.RoleRepository;
import tech.itmentors.crud.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).get();
    }

    @Override
    public void update(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteById(long id) {
        if (roleRepository.findById(id).isPresent())
            roleRepository.deleteById(id);
    }
}
