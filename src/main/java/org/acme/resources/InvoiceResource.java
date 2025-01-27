package org.acme.resources;

import java.net.URI;
import java.util.List;

import org.acme.model.Invoice;

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

@Path("/api/v1/invoices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvoiceResource {

	@GET
	public List<Invoice> list() {
		return Invoice.listAll();
	}

	@GET
	@Path("/{id}")
	public Invoice get(Long id) {
		return Invoice.findById(id);
	}

	@POST
	@Transactional
	public Response create(Invoice invoice) {
		invoice.persist();
		return Response.created(URI.create("/invoices/" + invoice.id)).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public Invoice update(Long id, Invoice invoice) {
		Invoice entity = Invoice.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}

		// map all fields from the invoice parameter to the existing entity
		//TODO
		//entity.number = invoice.number;

		return entity;
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void delete(Long id) {
		Invoice entity = Invoice.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}
		entity.delete();
	}

//    @GET
//    @Path("/search/{name}")
//    public Invoice search(String name) {
//        return Invoice.findByName(name);
//    }

	@GET
	@Path("/count")
	public Long count() {
		return Invoice.count();
	}
}