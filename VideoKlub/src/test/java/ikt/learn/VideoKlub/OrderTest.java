package ikt.learn.VideoKlub;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ikt.learn.VideoKlub.Exception.TotalCountException;
import ikt.learn.VideoKlub.Filter.OrderFilter;
import ikt.learn.VideoKlub.Model.Item;
import ikt.learn.VideoKlub.Model.Order;

public class OrderTest extends AbstractVideoTest {

	@Test
	public void testSaveOrder() {

		manager.getTransaction().begin();

		Item item1 = manager.find(Item.class, 1L);
		Item item2 = manager.find(Item.class, 2L);

		List<Item> items = new ArrayList<>();

		items.add(item1);
		items.add(item2);

		Order order = new Order();

		order.setItems(items);

		for (Item item : items) {
			item.setTotalCount(item.getTotalCount() - 1);
			item.setOrder(order);
		}
		manager.persist(order);

		manager.getTransaction().commit();

	}

	@Test
	public void testUpdateOrderAddItem() {

		OrderFilter filter = new OrderFilter();
		filter.setId(6L);

		Item item1 = manager.find(Item.class, 3L);
		Item item2 = manager.find(Item.class, 4L);

		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);

		Order order = manager.find(Order.class, filter.getId());

		try {
			addItem(order, items);
		} catch (TotalCountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addItem(Order order, List<Item> items) throws TotalCountException {

		manager.getTransaction().begin();

		for (Item item : items) {

			if (item.getTotalCount() <= 0)
				throw new TotalCountException("Item =" + item.getName() + " nije na stanju");

			item.setTotalCount(item.getTotalCount() - 1);
			item.setOrder(order);

		}
		order.getItems().addAll(items);

		manager.getTransaction().commit();
	}
}
