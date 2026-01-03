package org.mine.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.mine.domain.room.Room;
import org.mine.domain.room.RoomStatus;

import java.util.List;

@ApplicationScoped
public class RoomRepository implements PanacheRepository<Room> {

    public Uni<Boolean> existsByHostelAndRoomNumber(Long hotelId, String roomNumber) {
        return count(
                "hotel.id = ?1 and roomNumber = ?2 and status != ?3",
                hotelId, roomNumber, RoomStatus.DELETED
                ).map(count -> count > 0);
    }

    public Uni<List<Room>> findByHotel(final Long hotelId,
                                       final int page,
                                       final int size) {
        return find(
                "hotel.id = ?1 and status != ?2",
                hotelId, RoomStatus.DELETED
        ).page(page, size)
                .list();
    }
}
