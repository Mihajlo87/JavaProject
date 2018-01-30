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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS") //ne moze "ORDER" posto je order rezevisana rec u sql-u
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "DATUM_IZNAJMLJIVANJA", updatable = true, length = 128)
	private Long datumIznajmljivanja;

	@Column(name = "DATUM_VRACANJA", updatable = true, length = 128)
	private Long datumVracanja;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID", nullable = false, updatable = false)
	private Long id;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Item> items;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "TOTAL_PRICE", updatable = true, length = 128)
	private int totalPrice;

	public Long getDatumVracanja() {
		return datumVracanja;
	}

	public Long getId() {
		return id;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public Long getDatumIznajmljivanja() {
		return datumIznajmljivanja;
	}

	public void setDatumIznajmljivanja(Long datumIznajmljivanja) {
		this.datumIznajmljivanja = datumIznajmljivanja;
	}

	public void setDatumVracanja(Long datumVracanja) {
		this.datumVracanja = datumVracanja;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
