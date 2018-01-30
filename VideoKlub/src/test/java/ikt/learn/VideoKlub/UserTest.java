package ikt.learn.VideoKlub;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import ikt.learn.VideoKlub.Model.User;

public class UserTest extends AbstractVideoTest {

	@Test
	public void testDeleteUserById() {
		Long id = 5L;

		User user = manager.find(User.class, id);

		manager.getTransaction().begin();
		manager.remove(user);
		manager.getTransaction().commit();

		User user2 = manager.find(User.class, id);

		Assert.assertTrue(user2 == null);

	}

	@Test
	public void testFindUserById() {
		Long id = 5L;

		User user = manager.find(User.class, id);

		Assert.assertNotNull(user);

		System.out.println(user.getFirstName());
	}

	@Test
	public void testSaveUser() {

		Random random = new Random();
		int broj = random.nextInt(1000);

		User user = new User();

		user.setFirstName("test name");
		user.setLicnaKarta(12211 + broj);

		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();

		User newUser = manager.find(User.class, user.getId());

		Assert.assertNotNull(newUser);

	}

}
