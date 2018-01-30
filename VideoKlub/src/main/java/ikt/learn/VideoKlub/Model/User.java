package ikt.learn.VideoKlub.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "FIRST_NAME", length = 60, unique = false, updatable = true)
	private String firstName;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", nullable = false, updatable = false)
	private Long id;

	@Column(name = "LICNA_KARTA", length = 60, unique = true, updatable = true)
	private int licnaKarta;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Order> orders;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (licnaKarta != other.licnaKarta)
			return false;
		return true;
	}

	public String getFirstName() {
		return firstName;
	}

	public Long getId() {
		return id;
	}

	public int getLicnaKarta() {
		return licnaKarta;
	}

	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + licnaKarta;
		return result;
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

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
