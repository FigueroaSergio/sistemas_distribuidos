package services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.JAXBElement;

import datamodel.Item;
import datamodel.DaoItems;

public class ItemResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public ItemResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Item getItem() {
		Item item = DaoItems.instance.getModel().get(id);
		if (item == null)
			throw new RuntimeException("Get: Item " + id + " not found");
		return item;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putItem(JAXBElement<Item> item) {
		Item it = item.getValue();
		return putAndGetResponse(it);
	}

	@PUT
	@Path("dec")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response decItem(String number) {

		// EJERCICIO: decrementar la cantidad (definida por el par�metro number) del
		// item.
		Item item = DaoItems.instance.getModel().get(id);
		if (item == null)
			throw new RuntimeException("Get: Item " + id + " not found");
		item.decAmount(Long.parseLong(number));
		Response res = Response.ok().build();
		return res;
	}

	@PUT
	@Path("inc")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response dincItem(String number) {

		// EJERCICIO: incrementar la cantidad (definida por el par�metro number) del
		// item.
		Item item = DaoItems.instance.getModel().get(id);
		if (item == null)
			throw new RuntimeException("Get: Item " + id + " not found");
		item.incAmount(Long.parseLong(number));

		Response res = Response.ok().build();
		return res;
	}

	@DELETE
	public void deleteItem() {
		Item it = DaoItems.instance.getModel().remove(id);
		if (it == null)
			throw new RuntimeException("Delete: Item " + id + " not found");
	}

	private Response putAndGetResponse(Item item) {
		Response res;
		if (DaoItems.instance.getModel().containsKey(item.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		DaoItems.instance.getModel().put(item.getId(), item);
		return res;
	}
}
