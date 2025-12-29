package org.mine.application.user;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mine.Services.UserService;
import org.mine.dto.UserCreateDTO;
import org.mine.dto.UserResponseDTO;

@ApplicationScoped
public class CreateUserUseCase {

    @Inject
    UserService userService;

    public Uni<UserResponseDTO> execute(UserCreateDTO dto) {
        return userService.create(dto);
    }
}
