package ikt.learn.VideoKlub;

import java.util.List;

import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;

import ikt.learn.VideoKlub.Model.Item;

@SuppressWarnings("unchecked")
public class ItemTest extends AbstractVideoTest {

	@Test
	public void testRemoveItemById() {

		Long id = 8L;
		Item item = manager.find(Item.class, id);

		manager.getTransaction().begin();
		manager.remove(item);
		manager.getTransaction().commit();

		Item item1 = manager.find(Item.class, id);

		Assert.assertTrue(item1 == null);

	}

	@Test
	public void testAddItem() {

		Item item = new Item();
		item.setName("Test name");
		item.setTotalCount(10);

		manager.getTransaction().begin();
		manager.persist(item);
		manager.getTransaction().commit();

		Item item2 = manager.find(Item.class, item.getId());

		Assert.assertNotNull(item2);

		System.out.println(item2.getName());
	}

	@Test
	public void testFindItemById() {

		Long id = 9L;
		Item item = manager.find(Item.class, id);

		Assert.assertNotNull(item);

		System.out.println(item.getName());

	}

	@Test
	public void testFindItemByKey() {

		String name = "%Test%";

		Query query = manager.createQuery("SELECT i FROM Item i WHERE i.name like :parametar");
		query.setParameter("parametar", name);

		List<Item> items = query.getResultList();

		Assert.assertNotNull(items);
		Assert.assertTrue(!items.isEmpty());
		Assert.assertTrue(items.size() == 1);

	}
}
