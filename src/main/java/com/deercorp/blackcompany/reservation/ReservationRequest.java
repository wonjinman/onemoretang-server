package com.deercorp.blackcompany.reservation;

import lombok.Data;

@Data

public class ReservationRequest {
    private String name;
    private String carNumber;
    private String type;
}
