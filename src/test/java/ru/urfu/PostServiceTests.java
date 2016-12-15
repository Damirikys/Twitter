package ru.urfu;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.urfu.entities.Post;
import ru.urfu.entities.User;
import ru.urfu.mockable.InMemoryPostDatabaseImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTests
{
    private static InMemoryPostDatabaseImpl postDatabase = InMemoryPostDatabaseImpl.getInstance();
    private static User testUser;

    @BeforeClass
    public static void before()
    {
        testUser = new User();
        testUser.setId(0);

        Post post = new Post();
        post.setText("Test post");
        post.setUserId(testUser.getId());

        postDatabase.create(post);
    }

    @Test
    public void createValidPost() {
        Post post = new Post();
        post.setText("New post valid.");
        post.setUserId(testUser.getId());

        postDatabase.create(post);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidPostText()
    {
        Post post = new Post();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 200; i++)
            builder.append("very invalid post text");

        post.setText(builder.toString());
        post.setUserId(0);

        postDatabase.create(post);
    }

    @Test
    public void findPostById() {
        assert postDatabase.getPostById(0).isPresent();
    }

    @Test
    public void findUserPosts() {
        postDatabase.getUserPosts(testUser).forEach(post -> {
            assert post.getUserId() == testUser.getId();
        });
    }
}
