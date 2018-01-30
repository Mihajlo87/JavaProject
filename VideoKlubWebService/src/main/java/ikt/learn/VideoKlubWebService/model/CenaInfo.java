package ikt.learn.VideoKlubWebService.model;

public class CenaInfo {
	private long id;

	private Long[] itemIds;

	private int licnaKarta;

	public long getId() {
		return id;
	}

	public Long[] getItemIds() {
		return itemIds;
	}

	public void setItemIds(Long[] itemIds) {
		this.itemIds = itemIds;
	}

	public int getLicnaKarta() {
		return licnaKarta;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLicnaKarta(int licnaKarta) {
		this.licnaKarta = licnaKarta;
	}

}
