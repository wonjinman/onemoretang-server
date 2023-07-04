package com.deercorp.blackcompany.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationRepository reservationRepository;
    @PostMapping("/reservation")
    public void createReservation(@RequestHeader("x-device-key") String deviceKey, @RequestBody ReservationRequest request) {
        ReservationEntity reservationEntity = new ReservationEntity(request.getName(), request.getCarNumber(), request.getType(),deviceKey);
        reservationRepository.save(reservationEntity);
    }
}
