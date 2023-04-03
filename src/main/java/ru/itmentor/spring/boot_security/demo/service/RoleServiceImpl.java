package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dao.RoleRepository;
import ru.itmentor.spring.boot_security.demo.entity.Role;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * @param role
     */
    @Override
    public void save(Role role) {

    }

    /**
     * @return
     */
    @Override
    public List<Role> findAll() {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Role findById(long id) {
        return null;
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Role findByName(String name) {
        return null;
    }

    /**
     * @param role
     */
    @Override
    public void update(Role role) {

    }

    /**
     * @param id
     */
    @Override
    public void deleteById(long id) {

    }
}
