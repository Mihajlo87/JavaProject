package ikt.learn.VideoKlub.Services;

import java.util.List;

import ikt.learn.VideoKlub.Model.Item;

public interface IItemInterface {

	Long addItem(Item item);

	boolean removeItemById(Long itemId);

	Item findItemById(Long itemId);

	List<Item> findItemByKey(String key);

}
