package org.mine.Services;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.mine.Mappers.HotelMapper;
import org.mine.repository.HotelRepository;
import org.mine.domain.hotel.Hotel;
import org.mine.domain.hotel.HotelStatus;
import org.mine.dto.HotelCreationDTO;
import org.mine.dto.HotelResponseDTO;

import java.time.Instant;

public class HotelService {

    @Inject
    HotelRepository repository;
    @Inject
    HotelMapper mapper;

    public Uni<HotelResponseDTO> create(HotelCreationDTO dto) {
        Hotel hotel = mapper.toEntity(dto);
        hotel.status = HotelStatus.ACTIVE;
        hotel.createdAt = Instant.now();

        return repository.persist(hotel)
                .map(mapper::toDTO);
    }
}
