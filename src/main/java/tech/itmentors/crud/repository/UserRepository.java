package tech.itmentors.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.itmentors.crud.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

}
