package ikt.learn.VideoKlub;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

import ikt.learn.VideoKlub.Model.Item;
import ikt.learn.VideoKlub.Model.User;

public abstract class AbstractVideoTest {

	protected EntityManagerFactory factory;

	protected EntityManager manager;

	@Before
	public void setup() {
		factory = Persistence.createEntityManagerFactory("video_klub");
		manager = factory.createEntityManager();

		manager.getTransaction().begin();

		Item item1 = createItem();
		Item item2 = createItem();

		manager.persist(item1);
		manager.persist(item2);

		manager.getTransaction().commit();
	}

	@After
	public void shutdown() {
		manager.close();
		factory.close();
	}

	private Item createItem() {
		Random random = new Random();
		int broj = random.nextInt(1000);

		Item item = new Item();
		item.setName("Ime kasete " + broj);
		item.setTotalCount(10);

		return item;

	}

	private User createUser() {
		Random random = new Random();
		int broj = random.nextInt(1000);

		User user = new User();

		user.setLicnaKarta(454 + broj);
		user.setFirstName("Pera");

		return user;

	}


}
