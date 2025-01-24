package org.acme.resources;

import java.net.URI;
import java.util.List;

import org.acme.model.Order;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

	@GET
	public List<Order> list() {
		return Order.listAll();
	}

	@GET
	@Path("/{id}")
	public Order get(Long id) {
		return Order.findById(id);
	}

	@POST
	@Transactional
	public Response create(Order order) {
		order.persist();
		return Response.created(URI.create("/orders/" + order.id)).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public Order update(Long id, Order order) {
		Order entity = Order.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}

		// map all fields from the order parameter to the existing entity
		entity.name = order.name;

		return entity;
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void delete(Long id) {
		Order entity = Order.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}
		entity.delete();
	}

	// TODO
//    @GET
//    @Path("/search/{name}")
//    public Order search(String name) {
//        return Order.findByName(name);
//    }

	@GET
	@Path("/count")
	public Long count() {
		return Order.count();
	}
}