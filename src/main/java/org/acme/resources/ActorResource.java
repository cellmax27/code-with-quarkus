package org.acme.resources;

import java.net.URI;
import java.util.List;

import org.acme.model.Actor;

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

@Path("/api/v1/actors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActorResource {

	@GET
	public List<Actor> list() {
		return Actor.listAll();
	}

	@GET
	@Path("/{id}")
	public Actor get(Long id) {
		return Actor.findById(id);
	}

	@POST
	@Transactional
	public Response create(Actor actor) {
		actor.persist();
		return Response.created(URI.create("/actors/" + actor.id)).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public Actor update(Long id, Actor actor) {
		Actor entity = Actor.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}

		// map all fields from the actor parameter to the existing entity
		entity.name = actor.name;

		return entity;
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void delete(Long id) {
		Actor entity = Actor.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}
		entity.delete();
	}

	// TODO
//    @GET
//    @Path("/search/{name}")
//    public Actor search(String name) {
//        return Actor.findByName(name);
//    }

	@GET
	@Path("/count")
	public Long count() {
		return Actor.count();
	}
}