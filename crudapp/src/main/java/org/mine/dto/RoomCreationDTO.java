package org.mine.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RoomCreationDTO(

        @NotBlank
        String roomNumber,

        @NotBlank
        @Min(1)
        int capacity,

        @NotBlank
        Long hotelId
) {
}
