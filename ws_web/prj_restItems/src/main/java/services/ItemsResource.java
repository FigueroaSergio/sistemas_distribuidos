package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;


import datamodel.Item;
import datamodel.DaoItems;

@Path("/items")
public class ItemsResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Returns the list of items to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<Item>();
		items.addAll(DaoItems.instance.getModel().values());
		return items;
	}

	// Return the list of items for applications
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Item> getAllItemsApp() {

		// EJERCICIO: Crear una lista con todos los items y retornarla.
		List<Item> items = new ArrayList<Item>();
		items.addAll(DaoItems.instance.getModel().values());
		return items;

	}

	// Returns the number of items
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = DaoItems.instance.getModel().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newItem(
			@FormParam("id") String id,
			@FormParam("description") String description,
			@FormParam("amount") String amount,
			@Context HttpServletResponse servletResponse) throws IOException {

		// EJERCICIO: Crear un nuevo item utilizando los par�metros del Form.
		// EJERCICIO: A�adir el item creado al modelo de datos.
		long v = 0;
		try {
			v = Long.parseLong(amount);
		} catch (NumberFormatException e) {
			return;
		}
		Item item = new Item(id, description, v);
		DaoItems.instance.getModel().put(id, item);
	}

	// Tunnel
	@Path("{item}")
	public ItemResource getItem(@PathParam("item") String id) {

		// EJERCICIO: Crear un "ItemREsource" y retornarlo.

		return new ItemResource(uriInfo, request, id);
	}
}
