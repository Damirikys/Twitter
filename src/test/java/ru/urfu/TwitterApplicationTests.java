package ru.urfu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.geom.Point2D;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterApplicationTests {

	@Test
	public void addPostTest() {
		Post test_post = new Post("Test post");
		PostStorage.add(test_post);
		assert PostStorage.contains(test_post);
	}

	@Test
	public void addEmptyPostTest() {
		int start_count = PostStorage.count();
		Post test_post = new Post("");
		PostStorage.add(test_post);
		assert !PostStorage.contains(test_post);
		int finish_count = PostStorage.count();
		assert start_count == finish_count;
	}

	@Test
	public void removePostTest() {
		Post test_post = new Post("Test post");
		PostStorage.add(test_post);
		assert PostStorage.contains(test_post);
		assert PostStorage.count() == 1;
		PostStorage.remove(test_post);
		assert !PostStorage.contains(test_post);
		assert PostStorage.count() == 0;
	}

	@Test
	public void postsIdTest(){
		for (int i = 0; i < 1000; i++)
			PostStorage.add(new Post(i+""));

		for (int i = 0; i < PostStorage.count(); i++)
			assert PostStorage.get(i).getId() == i;
	}

}
