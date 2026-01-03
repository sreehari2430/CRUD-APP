package org.mine.dto;

import org.mine.domain.room.RoomStatus;

public record RoomResponseDTO(
        Long id,
        String roomnumber,
        int capacity,
        RoomStatus status,
        Long hotelId
) {}
