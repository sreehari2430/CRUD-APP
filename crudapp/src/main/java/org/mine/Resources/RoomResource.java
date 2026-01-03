package org.mine.Resources;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mine.Services.RoomService;
import org.mine.dto.RoomCreationDTO;
import org.mine.dto.RoomResponseDTO;

import java.util.List;

@Path("/room")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {

    @Inject
    RoomService service;

    @POST
    public Uni<Response> create(@Valid RoomCreationDTO dto) {
        return service.create(dto)
                .map(res ->
                        Response.status(201).entity(res)
                                .build());
    }

    @GET
    public Uni<List<RoomResponseDTO>> listByHotel(
            @PathParam("hoteId") Long hotelId,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        return service.findByHotel(hotelId, page, size);
    }

}
