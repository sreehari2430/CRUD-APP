package org.mine.Mappers;

import org.mapstruct.Mapper;
import org.mine.domain.hotel.Hotel;
import org.mine.dto.HotelCreationDTO;
import org.mine.dto.HotelResponseDTO;

@Mapper(componentModel = "cdi")
public interface HotelMapper {

    Hotel toEntity(HotelCreationDTO dto);

    HotelResponseDTO toDTO(Hotel hotel);
}
