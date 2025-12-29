package org.mine.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDTO (

    @NotBlank(message = "name is required")
    @Size(min = 3, max = 50)
    String name,

    @NotBlank(message = "email is required")
    @Email(message = "Invalid email format")
    String email
) {}
