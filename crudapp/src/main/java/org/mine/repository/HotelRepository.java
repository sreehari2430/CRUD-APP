package org.mine.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.mine.domain.hotel.Hotel;
import org.mine.domain.hotel.HotelStatus;

@ApplicationScoped
public class HotelRepository implements PanacheRepository<Hotel> {

    public Uni<Hotel> findActiveById(Long id) {
        return find("id = ?1 and status != ?2", id, HotelStatus.DELETED)
                .firstResult();
    }
}
