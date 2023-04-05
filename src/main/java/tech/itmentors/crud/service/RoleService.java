package tech.itmentors.crud.service;

import tech.itmentors.crud.model.Role;

import java.util.List;

public interface RoleService {

    void save(Role role);

    List<Role> findAll();

    Role findById(long id);

    Role findByName(String name);

    void update(Role role);

    void deleteById(long id);

}
