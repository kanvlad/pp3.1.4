package tech.itmentors.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.itmentors.crud.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    Optional<User> findByName(String name);

}
