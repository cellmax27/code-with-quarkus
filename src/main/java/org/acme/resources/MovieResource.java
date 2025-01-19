package org.acme.resources;

import java.net.URI;
import java.util.List;

import org.acme.model.Movie;

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

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

    @GET
    public List<Movie> list() {
        return Movie.listAll();
    }

    @GET
    @Path("/{id}")
    public Movie get(Long id) {
        return Movie.findById(id);
    }

    @POST
    @Transactional
    public Response create(Movie movie) {
        movie.persist();
        return Response.created(URI.create("/movies/" + movie.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Movie update(Long id, Movie movie) {
        Movie entity = Movie.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the movie parameter to the existing entity
        entity.name = movie.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Movie entity = Movie.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

    @GET
    @Path("/search/{name}")
    public Movie search(String name) {
        return Movie.findByName(name);
    }

    @GET
    @Path("/count")
    public Long count() {
        return Movie.count();
    }
}