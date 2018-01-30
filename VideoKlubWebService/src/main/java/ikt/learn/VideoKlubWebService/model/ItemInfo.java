package ikt.learn.VideoKlubWebService.model;

public class ItemInfo {

	private Long id;

	private String name;

	private int totalCount;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
