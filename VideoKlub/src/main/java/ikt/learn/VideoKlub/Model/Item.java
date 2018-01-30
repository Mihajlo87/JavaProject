package ikt.learn.VideoKlub.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM")
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ITEM_ID", nullable = false, updatable = false)
	private Long id;

	@Column(name = "NAME", updatable = true, unique = true, length = 128)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private Order order;

	@Column(name = "TOTAL_COUNT", updatable = true)
	private int totalCount;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Order getOrder() {
		return order;
	}

	public int getTotalCount() {
		return totalCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
