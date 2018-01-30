package ikt.learn.VideoKlub.Filter;

import java.util.List;

public class UserFilter {

	private long id;

	private List<Long> itemIds;

	private int licnaKarta;

	public long getId() {
		return id;
	}

	public int getLicnaKarta() {
		return licnaKarta;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Long> getItemIds() {
		return itemIds;
	}

	public void setItemIds(List<Long> itemIds) {
		this.itemIds = itemIds;
	}

	public void setLicnaKarta(int licnaKarta) {
		this.licnaKarta = licnaKarta;
	}

}
