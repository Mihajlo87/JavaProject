package ikt.learn.VideoKlubWebService.model;

public class OrderInfo {

	private String choice;

	private Long datumIznajmljivanja;

	private Long datumVracanja;

	private Long id;

	private ItemInfo[] itemInfos;

	private int totalPrice;

	public String getChoice() {
		return choice;
	}

	public Long getDatumVracanja() {
		return datumVracanja;
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

	public void setDatumVracanja(Long datumVracanja) {
		this.datumVracanja = datumVracanja;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDatumIznajmljivanja() {
		return datumIznajmljivanja;
	}

	public void setDatumIznajmljivanja(Long datumIznajmljivanja) {
		this.datumIznajmljivanja = datumIznajmljivanja;
	}

	public ItemInfo[] getItemInfos() {
		return itemInfos;
	}

	public void setItemInfos(ItemInfo[] itemInfos) {
		this.itemInfos = itemInfos;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
