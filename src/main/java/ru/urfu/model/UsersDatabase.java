package ru.urfu.model;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Repository;
import ru.urfu.entities.User;
import ru.urfu.model.interfaces.UserDao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class UsersDatabase implements UserDao
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createQuery("from " + User.class.getName(), User.class)
                .getResultList();
    }

    @Override
    public Optional<User> find(int id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Transactional
    @Override
    public void create(User user) throws BadCredentialsException {
        if (!getUser(user.getUsername()).isPresent())
            em.persist(user);
        else throw new BadCredentialsException("User already exist.");
    }

    @Transactional
    @Override
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public Optional<User> getUser(String username)
    {
        String query = "select p from "
                + User.class.getName()
                + " p where login='"
                + username + "'";
        try
        {
            return Optional.of(em.createQuery(query, User.class).getSingleResult());
        }
        catch (NoResultException e)
        {
            return Optional.ofNullable(null);
        }
    }
}
