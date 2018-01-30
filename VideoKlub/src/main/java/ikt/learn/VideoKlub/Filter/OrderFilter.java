package ikt.learn.VideoKlub.Filter;

import java.util.List;

import ikt.learn.VideoKlub.Model.Item;

public class OrderFilter {

	private String choice;

	private int danIznajmljivanja;

	private int danVracanja;

	private Long id;

	private List<Item> items;

	private int totalPrice;

	public String getChoice() {
		return choice;
	}

	public int getDanIznajmljivanja() {
		return danIznajmljivanja;
	}

	public int getDanVracanja() {
		return danVracanja;
	}

	public Long getId() {
		return id;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public void setDanIznajmljivanja(int danIznajmljivanja) {
		this.danIznajmljivanja = danIznajmljivanja;
	}

	public void setDanVracanja(int danVracanja) {
		this.danVracanja = danVracanja;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
