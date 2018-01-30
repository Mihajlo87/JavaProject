package ikt.learn.VideoKlubWebService.model;

public class UserInfo {

	private String firstName;

	private Long id;

	private int licnaKarta;

	private OrderInfo[] orderInfos;

	public String getFirstName() {
		return firstName;
	}

	public Long getId() {
		return id;
	}

	public int getLicnaKarta() {
		return licnaKarta;
	}

	public OrderInfo[] getOrderInfos() {
		return orderInfos;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLicnaKarta(int licnaKarta) {
		this.licnaKarta = licnaKarta;
	}

	public void setOrderInfos(OrderInfo[] orderInfos) {
		this.orderInfos = orderInfos;
	}

}
