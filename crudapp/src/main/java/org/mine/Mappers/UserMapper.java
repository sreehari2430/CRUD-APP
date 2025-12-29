package org.mine.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mine.domain.user.User;
import org.mine.dto.UserCreateDTO;
import org.mine.dto.UserResponseDTO;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    //DTO -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    User toEntity(UserCreateDTO dto);

    //Entity -> DTO
    UserResponseDTO toDTO(User user);

}
