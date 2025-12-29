package org.mine.dto;

import org.mine.domain.hotel.HotelStatus;

public record HotelResponseDTO(
        Long id,
        String name,
        String code,
        HotelStatus status
) {}
