package ikt.learn.VideoKlub.Services;

import java.util.List;

import javax.persistence.EntityManager;

import ikt.learn.VideoKlub.Exception.TotalCountException;
import ikt.learn.VideoKlub.Filter.OrderFilter;
import ikt.learn.VideoKlub.Model.Item;
import ikt.learn.VideoKlub.Model.Order;
import ikt.learn.VideoKlub.Util.EntityManagerUtil;

public class OrderServiceImpl implements IOrderInterface {

	private static OrderServiceImpl INSTANCE = new OrderServiceImpl();

	public static OrderServiceImpl getInstance() {
		return INSTANCE;
	}

	private OrderServiceImpl() {

	}

	@Override // dodajemo/brisemo kasetu u orderu
	public Long updateOrder(OrderFilter filter) throws TotalCountException {
		EntityManager manager = EntityManagerUtil.getManager();

		Order order = manager.find(Order.class, filter.getId());

		if (filter.getChoice().equalsIgnoreCase("add"))

			addItem(order, filter.getItems());

		if (filter.getChoice().equalsIgnoreCase("remove"))
			removeItem(order, filter.getItems());

		return filter.getId();
	}

	private void addItem(Order order, List<Item> items) throws TotalCountException {

		EntityManager manager = EntityManagerUtil.getManager();

		manager.getTransaction().begin();

		for (Item item : items) {

			if (item.getTotalCount() <= 0)
				throw new TotalCountException("Item =" + item.getName() + " is not in the store");

			item.setTotalCount(item.getTotalCount() - 1);
			item.setOrder(order);

		}
		order.getItems().addAll(items);

		manager.getTransaction().commit();
	}

	private void removeItem(Order order, List<Item> items) {
		EntityManager manager = EntityManagerUtil.getManager();

		manager.getTransaction().begin();

		order.getItems().removeAll(items);

		for (Item item : items) {

			item.setTotalCount(item.getTotalCount() + 1);
			item.setOrder(null);
		}

	}

}
