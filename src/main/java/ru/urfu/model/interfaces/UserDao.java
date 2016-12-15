package ru.urfu.model.interfaces;

import org.springframework.security.authentication.BadCredentialsException;
import ru.urfu.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserDao
{
    Optional<User> find(int id);

    List<User> findAll();

    void create(User user) throws BadCredentialsException;

    void delete(User user);

    Optional<User> getUser(String username);
}