package org.mine.Resources;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import org.mine.Services.HotelService;
import org.mine.dto.HotelCreationDTO;

public class HotelResource {

    @Inject
    HotelService service;

    public Uni<Response> create(@Valid HotelCreationDTO dto) {
        return service.create(dto)
                .map(res ->
                        Response.status(201).entity(res).build()
                );
    }
}
