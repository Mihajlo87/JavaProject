package ikt.learn.VideoKlubWebService.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ikt.learn.VideoKlub.Exception.TotalCountException;
import ikt.learn.VideoKlub.Filter.UserFilter;
import ikt.learn.VideoKlub.Model.Item;
import ikt.learn.VideoKlub.Model.Order;
import ikt.learn.VideoKlub.Model.User;
import ikt.learn.VideoKlub.Services.ItemServiceImpl;
import ikt.learn.VideoKlub.Services.UserServiceImpl;
import ikt.learn.VideoKlubWebService.model.CenaInfo;
import ikt.learn.VideoKlubWebService.model.ItemInfo;
import ikt.learn.VideoKlubWebService.model.UserInfo;
import ikt.learn.VideoKlubWebService.model.OrderInfo;

@Path("user")
public class UserController {

	private UserServiceImpl userServiceImpl;

	// preko json-a saljemo usera sa listom ordera
	// posto order ima listu itema
	// trazimo iteme po id-i
	// setujemo ih u ordere i cuvamo ih sa sve user-om

	@POST
	@Path("/saveUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveUserAndHisOrders(UserInfo userInfo) {

		ItemServiceImpl itemServiceImpl = ItemServiceImpl.getInstance();
		userServiceImpl = UserServiceImpl.getInstance();

		User user = new User();
		user.setFirstName(userInfo.getFirstName());
		user.setLicnaKarta(userInfo.getLicnaKarta());

		OrderInfo[] orderInfos = userInfo.getOrderInfos();

		List<Order> orders = new ArrayList<>();

		if (Objects.nonNull(orderInfos))
			for (int i = 0; i < orderInfos.length; i++) {
				OrderInfo orderInfo = orderInfos[i];

				Order order = new Order();

				int totalPrice = totalPrice(orderInfo.getDatumIznajmljivanja(), orderInfo.getDatumVracanja());

				order.setId(orderInfo.getId());
				order.setDatumIznajmljivanja(order.getDatumIznajmljivanja());
				order.setDatumVracanja(order.getDatumVracanja());
				order.setTotalPrice(totalPrice);

				List<Item> items = new ArrayList<>();

				ItemInfo[] itemInfos = orderInfo.getItemInfos();

				for (ItemInfo itemInfo : itemInfos) {

					Long itemId = itemInfo.getId();

					Item item = itemServiceImpl.findItemById(itemId);

					item.setOrder(order);

					item.setTotalCount(item.getTotalCount() - 1);

					items.add(item);

				}

				order.setItems(items);

				order.setUser(user);
				orders.add(order);

			}

		user.setOrders(orders);
		try {
			userServiceImpl.saveUser(user);
		} catch (TotalCountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.ok(userInfo, MediaType.APPLICATION_JSON).build();

	}

	@POST
	@Path("/totalPrice")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response totalPrice(CenaInfo cenaInfo) {

		userServiceImpl = UserServiceImpl.getInstance();

		UserFilter filter = new UserFilter();
		filter.setId(cenaInfo.getId());
		filter.setLicnaKarta(cenaInfo.getLicnaKarta());

		List<Long> itemIds = new ArrayList<>();

		Long[] itemIds2 = cenaInfo.getItemIds();

		for (Long id : itemIds2) {
			itemIds.add(id);
		}
		filter.setItemIds(itemIds);

		int totalPrice = userServiceImpl.totalPrice(filter);

		return Response.ok(String.format("Total price is : %d", totalPrice), MediaType.APPLICATION_XML).build();
	}

	private int totalPrice(Long iznajmljivanje, Long vracanje) {

		Date date1 = new Date(iznajmljivanje);
		Date date2 = new Date(vracanje);
		long razlika = 0;
		razlika = date1.getTime() - date2.getTime();
		razlika = razlika / (1000 * 60 * 60 * 24);

		int razlikaUDanima = (int) razlika;

		if (razlikaUDanima < 3)
			return razlikaUDanima * 100;

		else
			return 2 * 100 + (razlikaUDanima - 2) * 200;
	}
}
