package org.mine.domain.hotel;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "hotels")
public class Hotel extends PanacheEntity {

    @Column(name = "hotel_id", nullable = false, unique = true)
    public String code;

    @Column(nullable = false)
    public String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public HotelStatus status;

    @Column(nullable = false)
    public Instant createdAt;
}
