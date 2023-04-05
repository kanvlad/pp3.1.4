package tech.itmentors.crud.service;


import tech.itmentors.crud.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    List<User> findAll();

    User findById(long id);

    User findByName(String name);

    User update(User user);

    void deleteById(long id);

}
