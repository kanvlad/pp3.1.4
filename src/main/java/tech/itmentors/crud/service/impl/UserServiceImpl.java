package tech.itmentors.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.itmentors.crud.exception.NotAcceptableException;
import tech.itmentors.crud.exception.NotFoundException;
import tech.itmentors.crud.model.User;
import tech.itmentors.crud.repository.UserRepository;
import tech.itmentors.crud.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        List<User> users = userRepository.findAll();

        for (User u : users) {
            if (user.getName().equals(u.getName())) {
                throw new NotAcceptableException("Not Acceptable: User with this name exists");
            }
        }

        if (user.getId() != null) {
            throw new NotAcceptableException(
                    String.format("Not Acceptable: ID must be 0 to create user this id = %d", user.getId()));
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new NotFoundException("Not Found: Users is not found");
        return users;
    }

    @Override
    public User findById(long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Not Found: User by id = %d is not found", id)));
    }

    @Override
    public User findByName(String name) {
        return userRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Not Found: User by name = %s is not found", name)));
    }

    @Override
    public User update(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("Not Found: Trying to update non-existent a user");
        }

        if (user.getId() == null) {
            throw new NotAcceptableException(
                    String.format("Not Acceptable: ID must not be 0 to create user this id = %d", user.getId()));
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException(String.format("Not Found: User by id = %d is not found", id));
        }
        userRepository.deleteById(id);
    }
}
