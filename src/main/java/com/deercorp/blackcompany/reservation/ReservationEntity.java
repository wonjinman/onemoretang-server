package com.deercorp.blackcompany.reservation;

import com.deercorp.blackcompany.BaseDateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "reservation")
@NoArgsConstructor
public class ReservationEntity extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String carNumber;
    private String type;
    private String createdBy;
    private LocalDateTime deletedAt;

    public ReservationEntity(String name, String carNumber, String type, String createdBy) {
        this.name = name;
        this.carNumber = carNumber;
        this.type = type;
        this.createdBy = createdBy;
    }
}
