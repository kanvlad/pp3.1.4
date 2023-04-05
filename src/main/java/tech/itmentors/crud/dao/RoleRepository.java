package tech.itmentors.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.itmentors.crud.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
