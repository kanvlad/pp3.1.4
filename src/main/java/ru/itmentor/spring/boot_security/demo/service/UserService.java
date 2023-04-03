package ru.itmentor.spring.boot_security.demo.service;


import ru.itmentor.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<User> findAll();

    User findById(long id);

    User findByName(String name);

    void update(User user);

    void deleteById(long id);

}
