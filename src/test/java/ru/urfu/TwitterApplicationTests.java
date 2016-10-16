package ru.urfu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.geom.Point2D;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterApplicationTests {
	private User user = new User("Damir Armanov");
	private PostStorage postStorage = user.getPostStorage();

	@Test
	public void addPostTest() {
		Post test_post = new Post("Test post");
		postStorage.add(test_post);
		assert postStorage.contains(test_post);
	}

	@Test
	public void addEmptyPostTest() {
		int start_count = postStorage.count();
		Post test_post = new Post("");
		postStorage.add(test_post);
		assert !postStorage.contains(test_post);
		int finish_count = postStorage.count();
		assert start_count == finish_count;
	}

	@Test
	public void removePostTest() {
		Post test_post = new Post("Test post");
		postStorage.add(test_post);
		assert postStorage.contains(test_post);
		assert postStorage.count() == 1;
		postStorage.remove(test_post);
		assert !postStorage.contains(test_post);
		assert postStorage.count() == 0;
	}

	@Test
	public void postsIdTest(){
		for (int i = 0; i < 1000; i++)
			postStorage.add(new Post(i+""));

		for (int i = 0; i < postStorage.count(); i++)
			assert postStorage.get(i).getId() == i;
	}

}
