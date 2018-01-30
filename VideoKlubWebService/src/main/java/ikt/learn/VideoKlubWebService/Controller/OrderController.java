package ikt.learn.VideoKlubWebService.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ikt.learn.VideoKlub.Exception.TotalCountException;
import ikt.learn.VideoKlub.Filter.OrderFilter;
import ikt.learn.VideoKlub.Model.Item;
import ikt.learn.VideoKlub.Services.OrderServiceImpl;
import ikt.learn.VideoKlubWebService.model.ItemInfo;
import ikt.learn.VideoKlubWebService.model.OrderInfo;

@Path("order")
public class OrderController {

	private OrderServiceImpl orderServiceImpl;

	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrder(OrderInfo orderInfo) throws TotalCountException {

		orderServiceImpl = OrderServiceImpl.getInstance();

		OrderFilter filter = new OrderFilter();
		filter.setChoice(filter.getChoice());
		filter.setDanIznajmljivanja(filter.getDanIznajmljivanja());
		filter.setDanVracanja(filter.getDanVracanja());

		filter.setTotalPrice(filter.getTotalPrice());

		ItemInfo[] itemInfos = orderInfo.getItemInfos();

		List<Item> items = new ArrayList<>();

		if (Objects.nonNull(itemInfos))
			for (int i = 0; i < itemInfos.length; i++) {
				ItemInfo itemInfo = itemInfos[i];

				Item item = new Item();
				item.setId(itemInfo.getId());
				item.setName(itemInfo.getName());
				item.setTotalCount(itemInfo.getTotalCount());

				items.add(item);
			}
		filter.setItems(items);

		Long orderId = orderServiceImpl.updateOrder(filter);

		return Response.ok("Order with id" + orderId + " is successfully updated", MediaType.APPLICATION_XML).build();
	}

}
