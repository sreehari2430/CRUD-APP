package org.mine.Mappers;

import org.mine.Models.User;
import org.mine.Resources.UserResource;
import org.mine.dto.UserResponseDTO;

public class UserMapper {

    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.id = user.id;
        dto.name = user.name;
        dto.email = user.email;
        return dto;
    }
}
