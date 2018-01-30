package ikt.learn.VideoKlubWebService.Controller;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ikt.learn.VideoKlub.Model.Item;
import ikt.learn.VideoKlub.Services.ItemServiceImpl;
import ikt.learn.VideoKlubWebService.model.ItemInfo;

@Path("item")
public class ItemController {

	private ItemServiceImpl itemServiceImpl;

	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addItem(ItemInfo info) {

		itemServiceImpl = ItemServiceImpl.getInstance();

		Item item = new Item();
		item.setId(info.getId());
		item.setName(info.getName());
		item.setTotalCount(info.getTotalCount());

		Long itemId = itemServiceImpl.addItem(item);

		return Response.ok(String.format("New item is added to the video club.ID %d", itemId), MediaType.APPLICATION_XML)
				.build();
	}

	@GET
	@Path("remove/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response removeItem(@PathParam("id") Long id) {

		itemServiceImpl = ItemServiceImpl.getInstance();

		if (itemServiceImpl.removeItemById(id))
			return Response.ok("Item with id=" + id + " is removed from video club ", MediaType.APPLICATION_XML).build();

		return Response.ok("The item is not in the video club", MediaType.APPLICATION_XML).build();
	}

	@GET
	@Path("search/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findItemByKey(@PathParam("key") String key) {

		itemServiceImpl = ItemServiceImpl.getInstance();
		List<Item> items = itemServiceImpl.findItemByKey(key);

		ItemInfo[] itemInfos = new ItemInfo[items.size()];

		int i = 0;
		if (Objects.nonNull(items))
			for (Item item : items) {

				ItemInfo itemInfo = new ItemInfo();
				itemInfo.setId(item.getId());
				itemInfo.setName(item.getName());
				itemInfo.setTotalCount(item.getTotalCount());

				itemInfos[i] = itemInfo;
				i++;
			}

		return Response.ok(itemInfos, MediaType.APPLICATION_JSON).build();
	}

}
