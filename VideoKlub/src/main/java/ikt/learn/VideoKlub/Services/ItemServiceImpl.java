package ikt.learn.VideoKlub.Services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ikt.learn.VideoKlub.Model.Item;
import ikt.learn.VideoKlub.Util.EntityManagerUtil;

@SuppressWarnings("unchecked")
public class ItemServiceImpl implements IItemInterface {

	private static ItemServiceImpl INSTANCE = new ItemServiceImpl();

	public static ItemServiceImpl getInstance() {
		return INSTANCE;
	}

	private ItemServiceImpl() {

	}
	//Dodajemo item u bazu
	@Override
	public Long addItem(Item item) {

		EntityManager manager = EntityManagerUtil.getManager();

		manager.getTransaction().begin();
		manager.persist(item);
		manager.flush();
		manager.getTransaction().commit();
		manager.clear();

		return item.getId();
	}
	
	//Brisemo item iz baze
	@Override
	public boolean removeItemById(Long itemId) {
		EntityManager manager = EntityManagerUtil.getManager();

		Item item = manager.find(Item.class, itemId);

		if (item == null)
			return false;

		manager.getTransaction().begin();
		manager.remove(item);
		manager.flush();
		manager.getTransaction().commit();
		manager.clear();

		return true;

	}

	@Override
	public Item findItemById(Long itemId) {
		EntityManager manager = EntityManagerUtil.getManager();

		Item item = manager.find(Item.class, itemId);

		return item;
	}
	//Trazimo Iteme po kljucu
	@Override
	public List<Item> findItemByKey(String key) {
		EntityManager manager = EntityManagerUtil.getManager();

		Query query = manager.createQuery("SELECT i FROM Item i WHERE i.name like :parametar");
		query.setParameter("parametar", key);

		List<Item> items = query.getResultList();

		return items;
	}

}
