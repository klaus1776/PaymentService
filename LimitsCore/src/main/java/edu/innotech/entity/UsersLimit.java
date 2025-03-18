package edu.innotech.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "userslimits")
@NoArgsConstructor
public class UsersLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "limit_value")
    private Double limitValue;

    @Override
    public String toString() {
        return "Limit{" +
                "id=" + id +
                ", userId=" + userId +
                ", limitValue=" + limitValue +
                '}';
    }
}
