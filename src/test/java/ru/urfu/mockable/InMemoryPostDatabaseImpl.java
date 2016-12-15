package ru.urfu.mockable;

import ru.urfu.entities.Post;
import ru.urfu.entities.User;
import ru.urfu.model.interfaces.PostDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryPostDatabaseImpl implements PostDao
{
    private static final InMemoryPostDatabaseImpl instance = new InMemoryPostDatabaseImpl();

    private final Map<Integer, Post> postStorage = new HashMap<>();

    @Override
    public Optional<Post> getPostById(int id) {
        return Optional.ofNullable(postStorage.get(id));
    }

    @Override
    public List<Post> getUserPosts(User user)
    {
        return postStorage.values().stream()
                .filter(post -> (post.getUserId() == user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void create(Post post) {
        postStorage.put(post.getId(), post);
    }

    @Override
    public void delete(Post post) {
        postStorage.remove(post.getId());
    }

    public static InMemoryPostDatabaseImpl getInstance() {
        return instance;
    }
}
