package org.mine.Services;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.mine.Mappers.RoomMapper;
import org.mine.domain.room.Room;
import org.mine.domain.room.RoomStatus;
import org.mine.dto.RoomCreationDTO;
import org.mine.dto.RoomResponseDTO;
import org.mine.repository.HotelRepository;
import org.mine.repository.RoomRepository;

import java.util.List;

@ApplicationScoped
public class RoomService {

    @Inject
    RoomRepository roomRepository;

    @Inject
    HotelRepository hotelRepository;

    @Inject
    RoomMapper mapper;

    public Uni<RoomResponseDTO> create(RoomCreationDTO dto) {

        return hotelRepository.findActiveById(dto.hotelId())
                .onItem()
                .ifNull().failWith(() ->
                        new WebApplicationException("Hotel not found", 404))
                .flatMap(hotel ->
                        roomRepository.existsByHostelAndRoomNumber(hotel.id, dto.roomNumber())
                                .flatMap(exits -> {
                                    if (exits) {
                                        return Uni.createFrom().failure(
                                                new WebApplicationException("Room already exits", 409)
                                        );
                                    }

                                    Room room = mapper.toEntity(dto);
                                    room.hotel = hotel;
                                    room.status = RoomStatus.AVAILABLE;

                                    return roomRepository.persist(room);
                                })
                ).map(mapper::toDTO);
    }

    public Uni<List<RoomResponseDTO>> findByHotel(final Long hotelId,
                                                  final int page,
                                                  final int size) {
        return hotelRepository.findActiveById(hotelId)
                .onItem()
                .ifNull().failWith(() ->
                        new WebApplicationException("Hotel not found", 404)
                )
                .flatMap(hotel ->
                        roomRepository.findByHotel(
                                        hotel.id,
                                        page,
                                        size
                                )
                                .map(rooms ->
                                        rooms.stream()
                                                .map(mapper::toDTO)
                                                .toList()
                                )
                        );
    }

}
