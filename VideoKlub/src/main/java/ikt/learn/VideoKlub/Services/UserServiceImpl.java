package ikt.learn.VideoKlub.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import ikt.learn.VideoKlub.Exception.TotalCountException;
import ikt.learn.VideoKlub.Filter.UserFilter;
import ikt.learn.VideoKlub.Model.Item;
import ikt.learn.VideoKlub.Model.User;
import ikt.learn.VideoKlub.Model.Order;
import ikt.learn.VideoKlub.Util.EntityManagerUtil;

public class UserServiceImpl implements IUserInterface {

	private static UserServiceImpl INSTANCE = new UserServiceImpl();

	public static UserServiceImpl getInstance() {
		return INSTANCE;
	}

	private UserServiceImpl() {

	}

	// Brisemo User-a iz baze
	@Override
	public boolean deleteUserById(Long id) {

		EntityManager manager = EntityManagerUtil.getManager();

		User user = manager.find(User.class, id);

		if (user == null)
			return false;

		manager.getTransaction().begin();
		manager.remove(user);
		manager.flush();
		manager.getTransaction().commit();
		manager.clear();

		return true;
	}

	@Override
	public User findByUserId(Long id) {

		EntityManager manager = EntityManagerUtil.getManager();

		User user = manager.find(User.class, id);

		if (user == null)
			return null;

		return user;
	}

	//cuvamo novog korisnika i njegove order-e
	@Override
	public Long saveUser(User user) throws TotalCountException {

		EntityManager manager = EntityManagerUtil.getManager();

		manager.getTransaction().begin();
		manager.persist(user);

		List<Order> orders = user.getOrders();

		List<Item> items = new ArrayList<>();

		for (Order order : orders) { // stavljamo item-e koje ima sadrzi order u listu itema
										
			items.addAll(order.getItems());
		}

		for (Item item : items) {
			if (item.getTotalCount() <= 0)
				throw new TotalCountException("Item =" + item.getName() + " is not in the store");
		}

		for (Order order : orders) {
			manager.persist(order);
		}

		manager.flush();
		manager.getTransaction().commit();
		manager.clear();

		return user.getId();
	}
	
	//Racunamo koliko korisnik treba da plati 
	@Override
	public int totalPrice(UserFilter filter) {

		EntityManager manager = EntityManagerUtil.getManager();

		User user = manager.find(User.class, filter.getId());

		List<Order> orders = user.getOrders();

		long razlika = 0L;
		Date iznajmljivanje = null;
		Date vracanje = null;

		for (Order order : orders) {

			iznajmljivanje = new Date(order.getDatumIznajmljivanja());
			vracanje = new Date(order.getDatumVracanja());

			razlika = iznajmljivanje.getTime() - vracanje.getTime();
			razlika = razlika / (1000 * 60 * 60 * 24);
		}

		int razlikaUDanima = (int) razlika;

		if (razlikaUDanima < 3)
			return razlikaUDanima * 100;

		else
			return 2 * 100 + (razlikaUDanima - 2) * 200;
	}

}
