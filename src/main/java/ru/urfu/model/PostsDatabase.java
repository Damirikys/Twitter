package ru.urfu.model;

import org.springframework.stereotype.Repository;
import ru.urfu.entities.Post;
import ru.urfu.entities.User;
import ru.urfu.model.interfaces.PostDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public class PostsDatabase implements PostDao
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Post> getPostById(int id)
    {
        String query = "select p from " + Post.class.getName() + " p where id=" + id + "";
        return Optional.ofNullable(em.createQuery(query, Post.class).getSingleResult());
    }

    @Override
    public List<Post> getUserPosts(User user)
    {
        int userId = user.getId();
        String query = "select p from " + Post.class.getName() + " p where userId=" + userId + "";
        return em.createQuery(query, Post.class).getResultList();
    }

    @Transactional
    @Override
    public void create(Post post) {
        em.persist(post);
    }

    @Transactional
    @Override
    public void delete(Post post) {
        em.remove(post);
    }
}
