package org.mine.domain.room;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;
import org.mine.domain.hotel.Hotel;

@Entity
@Table(name = "rooms", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"hotel_id", "room_number"})
})
public class Room extends PanacheEntity {

    @Column(name = "room_number", nullable = false)
    public String roomNumber;

    @Column(name = "capacity", nullable = false)
    public int capacity;

    @Column(name = "room_status", nullable = false)
    @Enumerated(EnumType.STRING)
    public RoomStatus status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    public Hotel hotel;
}
