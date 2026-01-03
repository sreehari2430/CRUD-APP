package org.mine.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mine.domain.room.Room;
import org.mine.dto.RoomCreationDTO;
import org.mine.dto.RoomResponseDTO;

@Mapper(componentModel = "cdi")
public interface RoomMapper {

    //DTO -> Entity
    @Mapping(target = "hotel", ignore = true)
    Room toEntity(RoomCreationDTO dto);

    //Entity -> DTO
    @Mapping(source = "hotel.id", target = "hotelId")
    RoomResponseDTO toDTO(Room room);
}

