package tech.itmentors.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.itmentors.crud.dao.UserRepository;
import tech.itmentors.crud.model.User;

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
    public void save(User user) {
        Optional<User> optionalUser = userRepository.findByName(user.getName());
        if (optionalUser.isEmpty()) {
            userRepository.save(user);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByName(String name) {
        Optional<User> user = userRepository.findByName(name);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return user.get();
    }
    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
