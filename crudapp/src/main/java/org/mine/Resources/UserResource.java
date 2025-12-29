package org.mine.Resources;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mine.Services.UserService;
import org.mine.application.user.CreateUserUseCase;
import org.mine.dto.UserCreateDTO;
import org.mine.dto.UserResponseDTO;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserResource {

    @Inject
    UserService userService;

    @Inject
    CreateUserUseCase createUser;

    @POST
    public Uni<Response> create(@Valid UserCreateDTO dto) {
        return createUser.execute(dto)
                .map(user ->
                        Response.status(Response.Status.CREATED)
                                .entity(user)
                                .build()
                );
    }

    @GET
    @Path("/{id}")
    public Uni<UserResponseDTO> find(@PathParam("id") Long id) {
        return userService.find(id);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Boolean> delete(@PathParam("id") Long id) {
        return userService.delete(id);
    }

    @GET
    public Uni<List<UserResponseDTO>> findAll() {
        return userService.findAll(2,2);
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@PathParam("id") Long id, UserCreateDTO user) {
        return userService.update(id, user)
                .onItem().ifNotNull().transform(updated -> Response.ok(updated).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.BAD_GATEWAY).build());
    }
}
