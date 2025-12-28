package org.mine.Resources;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mine.Models.User;
import org.mine.Services.UserService;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserResource {

    @Inject
    UserService userService;

    @POST
    public Uni<User> create(User user) {
        return userService.create(user);
    }

    @GET
    @Path("/{id}")
    public Uni<User> find(@PathParam("id") Long id) {
        return userService.find(id);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Boolean> delete(@PathParam("id") Long id) {
        return userService.delete(id);
    }

    @GET
    public Uni<List<User>> findAll() {
        return userService.findAll();
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@PathParam("id") Long id, User user) {
        return userService.update(id, user)
                .onItem().ifNotNull().transform(updated -> Response.ok(updated).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.BAD_GATEWAY).build());
    }
}
