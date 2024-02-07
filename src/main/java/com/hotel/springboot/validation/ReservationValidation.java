package com.hotel.springboot.validation;

import com.hotel.springboot.payload.Reservation;
import com.hotel.springboot.exception.ReservationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReservationValidation {

    public static boolean isValidReservationDate (Reservation reservation) throws ReservationException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate actualDate = LocalDate.now();
        LocalDate checkInDate = LocalDate.parse(reservation.getCheckIn(),dtf);
        LocalDate checkOutDate = LocalDate.parse(reservation.getCheckOut(),dtf);

        if(checkInDate.isBefore(checkOutDate)){
            return checkInDate.isAfter(actualDate) || checkInDate.isEqual(actualDate);
        }
        return false;
    }
    public static boolean isValidReservationAge (Reservation reservation){

      List<Integer> ages = reservation.getAges();

      return ages.stream()
              .allMatch(age->age>0 && age<100)
              && ages.stream().anyMatch(age -> age>=18);
    }
}
