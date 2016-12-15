package ru.urfu.mockable;

import org.springframework.security.authentication.BadCredentialsException;
import ru.urfu.entities.User;
import ru.urfu.model.interfaces.UserDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryUserDatabaseImpl implements UserDao
{
    private static final InMemoryUserDatabaseImpl instance = new InMemoryUserDatabaseImpl();

    private final Map<Integer, User> userStorage = new HashMap<>();

    @Override
    public Optional<User> find(int id) {
        return Optional.ofNullable(userStorage.get(id));
    }

    @Override
    public List<User> findAll() {
        return userStorage.values().stream().collect(Collectors.toList());
    }

    @Override
    public void create(User user) throws BadCredentialsException {
        if (!getUser(user.getUsername()).isPresent())
            userStorage.put(user.getId(), user);
        else throw new BadCredentialsException("User already exist.");
    }

    @Override
    public void delete(User user) {
        userStorage.get(user.getId());
    }

    @Override
    public Optional<User> getUser(String username) {
        return userStorage.values().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    public static InMemoryUserDatabaseImpl getInstance() {
        return instance;
    }
}
