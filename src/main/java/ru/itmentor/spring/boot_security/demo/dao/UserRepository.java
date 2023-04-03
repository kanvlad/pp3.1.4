package ru.itmentor.spring.boot_security.demo.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);

    Optional<User> findByName(String name);
}
