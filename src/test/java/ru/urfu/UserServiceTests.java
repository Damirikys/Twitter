package ru.urfu;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit4.SpringRunner;
import ru.urfu.auth.SecurityConfig;
import ru.urfu.entities.User;
import ru.urfu.mockable.InMemoryUserDatabaseImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests
{
	private static InMemoryUserDatabaseImpl userDatabase = InMemoryUserDatabaseImpl.getInstance();
	private static User testUser;

	@BeforeClass
	public static void before()
	{
		User user = new User();
		user.setUsername("username");
		user.setPassword(SecurityConfig.byteArrayToHexString("password".getBytes()));

		userDatabase.create(user);
		testUser = user;
	}

	@Test
	public void createNewValidUser()
	{
		User user = new User();
		user.setUsername("username2");
		user.setPassword(SecurityConfig.byteArrayToHexString("password2".getBytes()));

		userDatabase.create(user);
	}

	@Test(expected =  IllegalArgumentException.class)
	public void createUserWithInvalidUsername()
	{
		User user = new User();
		user.setUsername("usernamewithveryveryverybiglengthusernamewithveryveryverybiglength");
		user.setPassword(SecurityConfig.byteArrayToHexString("password".getBytes()));

		userDatabase.create(user);
	}

	@Test(expected =  BadCredentialsException.class)
	public void createUserThatAlreadyExist()
	{
		User user = new User();
		user.setUsername("username");
		user.setPassword(SecurityConfig.byteArrayToHexString("password".getBytes()));

		userDatabase.create(user);
	}

	@Test
	public void findUserById() {
		assert userDatabase.find(0).isPresent();
	}

	@Test
	public void findUserByUsername() {
		assert userDatabase.getUser("username").isPresent();
	}

	@Test
	public void passwordEncryptTest() {
		assert SecurityConfig.byteArrayToHexString("password".getBytes()).equals(testUser.getPassword());
	}
}
