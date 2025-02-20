package org.acme.resources;

import java.net.URI;
import java.util.List;

import org.acme.model.Appointement;

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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/api/v1/appointements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointementResource {
    private Set<Appointement> appointements = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    
	@GET
	public List<Appointement> list() {
		return Appointement.listAll();
	}

	@GET
	@Path("/{id}")
	public Appointement get(Long id) {
		return Appointement.findById(id);
	}

	@POST
	@Transactional
	public Response create(Appointement appointement) {
		appointement.persist();
		return Response.created(URI.create("/appointements/" + appointement.id)).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public Appointement update(Long id, Appointement appointement) {
		Appointement entity = Appointement.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}

		// map all fields from the customer parameter to the existing entity
//TODO
		//entity.start = appointement.start;

		return entity;
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void delete(Long id) {
		Appointement appointement = Appointement.findById(id);
		if (appointement == null) {
			throw new NotFoundException();
		}
		appointement.delete();
		

	}

//    @GET
//    @Path("/search/{name}")
//    public Appointement search(String name) {
//        return Appointement.findByName(name);
//    }

	@GET
	@Path("/count")
	public Long count() {
		return Appointement.count();
	}
}