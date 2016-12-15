package ru.urfu.model.interfaces;

import ru.urfu.entities.Post;
import ru.urfu.entities.User;

import java.util.List;
import java.util.Optional;

public interface PostDao
{
    Optional<Post> getPostById(int id);

    List<Post> getUserPosts(User user);

    void create(Post post);

    void delete(Post post);
}
