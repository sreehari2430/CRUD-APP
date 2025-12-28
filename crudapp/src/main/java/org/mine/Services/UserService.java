package org.mine.Services;

import io.quarkus.hibernate.reactive.panache.Panache;
import jakarta.enterprise.context.ApplicationScoped;
import org.mine.Models.User;
import io.smallrye.mutiny.Uni;

import java.util.List;

@ApplicationScoped
public class UserService {

    public Uni<User> create(User user) {
        return Panache.withTransaction(() ->
                user.persist()
                .replaceWith(user));
    }

    public Uni<User> find(Long id) {
       return User.findById(id);
    }

    public Uni<Boolean> delete(Long id) {
        return User.deleteById(id);
    }

    public Uni<List<User>> findAll() {
        return User.listAll();
    }

    public Uni<User> update(Long id, User updatedUser) {
        return User.<User>findById(id)
                .onItem()
                .ifNull()
                .failWith(new RuntimeException("User not found"))
                .invoke(user -> {
                    user.name = updatedUser.name;
                    user.email = updatedUser.email;
                });
    }
}
