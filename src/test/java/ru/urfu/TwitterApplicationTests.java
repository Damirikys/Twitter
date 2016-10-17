package ru.urfu;

import org.junit.Assert;
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

	@Test(expected =  IllegalArgumentException.class)
	public void addEmptyPostText() {
		postStorage.add("");
	}

	@Test(expected =  IllegalArgumentException.class)
	public void addNullPostText() {
		postStorage.add(null);
	}

	@Test(expected =  IllegalArgumentException.class)
	public void addLimitLengthPostText() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 200; i++)
			stringBuilder.append(i);
		postStorage.add(stringBuilder.toString());
	}

	@Test
	public void addNormalPostText() {
		int oldCount = postStorage.count();
		postStorage.add("Normal text");
		Assert.assertEquals(oldCount + 1, postStorage.count());
	}

	@Test
	public void removePostItem() {
		postStorage.add("Test remove 1");
		postStorage.add("Test remove 2");
		postStorage.add("Test remove 3");
		postStorage.remove(1);

		Assert.assertEquals(2, postStorage.count());
	}

	@Test
	public void addNewUser() {
		int oldUsersCount = Users.size();
		User user = new User("Username");
		Assert.assertEquals(oldUsersCount + 1, Users.size());
	}

	@Test(expected =  IllegalArgumentException.class)
	public void addEmptyUserTextName() {
		User user = new User("");
	}

	@Test(expected =  IllegalArgumentException.class)
	public void addNullUserTextName() {
		User user = new User(null);
	}

	@Test(expected =  IllegalArgumentException.class)
	public void addLimitLengthUserTextName() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 100; i++)
			sb.append(i);

		User user = new User(sb.toString());
	}



}
