package org.mine.dto;

import jakarta.validation.constraints.NotBlank;

public record HotelCreationDTO(
        @NotBlank String name,
        @NotBlank String code
) {
}
