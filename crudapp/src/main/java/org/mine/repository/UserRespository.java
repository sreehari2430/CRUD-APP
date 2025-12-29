package org.mine.repository;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.mine.domain.user.User;

@ApplicationScoped
public class UserRespository {

    public Uni<User> findById(Long id) {
        return User.findById(id);
    }

    public Uni<User> findByEmail(String email) {
        return User.find("email", email).firstResult();
    }

    public Uni<User> persist(User user) {
        return user.persist();
    }
}
