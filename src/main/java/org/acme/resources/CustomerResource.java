package org.acme.resources;

import java.net.URI;
import java.util.List;

import org.acme.model.Customer;

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

@Path("/api/v1/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

	@GET
	public List<Customer> list() {
		return Customer.listAll();
	}

	@GET
	@Path("/{id}")
	public Customer get(Long id) {
		return Customer.findById(id);
	}

	@POST
	@Transactional
	public Response create(Customer customer) {
		customer.persist();
		return Response.created(URI.create("/customers/" + customer.id)).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public Customer update(Long id, Customer customer) {
		Customer entity = Customer.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}

		// map all fields from the customer parameter to the existing entity
		entity.name = customer.name;

		return entity;
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void delete(Long id) {
		Customer entity = Customer.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}
		entity.delete();
	}

//    @GET
//    @Path("/search/{name}")
//    public Customer search(String name) {
//        return Customer.findByName(name);
//    }

	@GET
	@Path("/count")
	public Long count() {
		return Customer.count();
	}
}