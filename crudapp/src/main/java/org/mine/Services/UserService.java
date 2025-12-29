package org.mine.Services;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mine.Mappers.UserMapper;
import org.mine.domain.user.User;
import io.smallrye.mutiny.Uni;
import org.mine.common.exception.BusinessException;
import org.mine.domain.user.UserStatus;
import org.mine.dto.UserCreateDTO;
import org.mine.dto.UserResponseDTO;
import org.mine.repository.UserRespository;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRespository userRespository;

    @Inject
    UserMapper mapper;

    public Uni<UserResponseDTO> create(UserCreateDTO dto) {
        return userRespository.findByEmail(dto.email())
                .onItem()
                .ifNotNull().failWith(() ->
                        new BusinessException("Email already exists"))
                .flatMap(v ->{
                    User user = mapper.toEntity(dto);
                    user.setStatus(UserStatus.ACTIVE);
                    return userRespository.persist(user)
                            .map(mapper::toDTO);
                });

    }

    public Uni<UserResponseDTO> find(Long id) {
       return User.findById(id)
               .onItem()
               .ifNull().failWith(() -> new RuntimeException("User not found"))
               .map(entity -> UserMapper.toDTO((User) entity));
    }

    public Uni<Boolean> delete(Long id) {
        return Panache.withTransaction(() ->
                User.deleteById(id)
        );
    }

    public Uni<List<UserResponseDTO>> findAll(int page, int size) {
        return User.findAll(Sort.by("name").ascending())
                .page(Page.of(page, size))
                .list()
                .map(users -> users.stream()
                        .map(u -> UserMapper.toDTO((User) u))
                        .toList()
                );
    }

    public Uni<User> update(Long id, UserCreateDTO dto) {
        return Panache.withTransaction(() ->
                User.<User>findById(id)
                        .onItem()
                        .ifNull().failWith(() ->
                                new RuntimeException("User not found"))
                        .invoke(user -> {
                                user.name = dto.name;
                                user.email = dto.email;
                        })
        );
    }
}
