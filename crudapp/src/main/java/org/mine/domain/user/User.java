package org.mine.domain.user;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, length = 50)
    public String name;

    @Column(nullable = false, unique = true)
    public String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public UserStatus status;

    public User() {}

    public void setStatus(UserStatus userStatus) {
        this.status = userStatus;
    }
}
