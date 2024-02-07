package com.hotel.springboot.validation;

import com.hotel.springboot.payload.Reservation;
import com.hotel.springboot.exception.ReservationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
public class ReservationValidationTest {

    @Test
    public void correctDateTest () throws ReservationException {

        Reservation reservation = reservationMock();

        boolean result = ReservationValidation.isValidReservationDate(reservation);

        assertTrue(result);
    }

    @Test
    public void incorrectDateTest () throws ReservationException {

        Reservation reservation = invalidDateReserveMock();

        boolean result = ReservationValidation.isValidReservationDate(reservation);

        assertFalse(result);
    }

    @Test
    public void pastDateTest (){

        Reservation reservation = pastDateReserveMock();

        boolean result = ReservationValidation.isValidReservationDate(reservation);

        assertFalse(result);
    }

    @Test
    public void correctAgeTest (){

        Reservation reservation = reservationMock();

        boolean result = ReservationValidation.isValidReservationAge(reservation);

        assertTrue(result);
    }

    @Test
    public void incorrectAgeTest (){

        Reservation invalidAgeReserveMock = invalidAgeReserveMock();

        boolean result = ReservationValidation.isValidReservationAge(invalidAgeReserveMock);

        assertFalse(result);
    }

    @Test
    public void singleAdultReservationTest (){

        Reservation reservation = singleAdultGuest();

        boolean result = ReservationValidation.isValidReservationAge(reservation);

        assertTrue(result);
    }

    public Reservation reservationMock (){
        return Reservation.createReservation("545","30/03/2024","10/10/2024", Arrays.asList(18,25));
    }

    public Reservation invalidDateReserveMock (){
        return Reservation.createReservation("545","30/03/2023","10/10/2024", Arrays.asList(18,99));
    }

    public Reservation invalidAgeReserveMock (){
        return Reservation.createReservation("545","30/03/2024","10/10/2024", Arrays.asList(18,100));
    }

    public Reservation singleAdultGuest (){
        return Reservation.createReservation("545","30/03/2024","10/10/2024", List.of(18));
    }

    public Reservation pastDateReserveMock (){
        return Reservation.createReservation("545","30/03/2024","10/10/2023", Arrays.asList(18,99));
    }

}
