package com.hotel.springboot.mapper;

import com.hotel.springboot.payload.Reservation;
import com.hotel.springboot.payload.ReservationResponse;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public ReservationResponse fromReservation(Reservation reservation){

        ReservationResponse reservationResponse = new ReservationResponse();

        reservationResponse.setHotelId(reservation.getHotelId());
        reservationResponse.setCheckIn(reservation.getCheckIn());
        reservationResponse.setCheckOut(reservation.getCheckOut());
        reservationResponse.setAges(reservation.getAges());

        return reservationResponse;
    }
}
