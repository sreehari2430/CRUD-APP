package org.mine.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateDTO {

    @NotBlank(message = "name is required")
    @Size(min = 3, max = 50)
    public String name;

    @NotBlank(message = "email is required")
    @Email(message = "Invalid email format")
    public String email;
}
