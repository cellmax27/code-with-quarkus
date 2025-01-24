package org.acme.resources;

import java.net.URI;
import java.util.List;

import org.acme.model.Salary;

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

@Path("/salarys")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SalaryResource {

	@GET
	public List<Salary> list() {
		return Salary.listAll();
	}

	@GET
	@Path("/{id}")
	public Salary get(Long id) {
		return Salary.findById(id);
	}

	@POST
	@Transactional
	public Response create(Salary salary) {
		salary.persist();
		return Response.created(URI.create("/salarys/" + salary.id)).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public Salary update(Long id, Salary salary) {
		Salary entity = Salary.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}

		// map all fields from the salary parameter to the existing entity
		entity.name = salary.name;

		return entity;
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void delete(Long id) {
		Salary entity = Salary.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}
		entity.delete();
	}

	// TODO
//    @GET
//    @Path("/search/{name}")
//    public Salary search(String name) {
//        return Salary.findByName(name);
//    }

	@GET
	@Path("/count")
	public Long count() {
		return Salary.count();
	}
}