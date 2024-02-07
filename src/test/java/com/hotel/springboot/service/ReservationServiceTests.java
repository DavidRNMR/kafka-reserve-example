package com.hotel.springboot.service;

import com.hotel.springboot.kafka.KafkaProducer;
import com.hotel.springboot.payload.Reservation;
import com.hotel.springboot.payload.Search;
import com.hotel.springboot.repository.ReservationRepository;
import com.hotel.springboot.exception.ReservationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTests {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private KafkaProducer kafkaProducer;

    @InjectMocks
    private ReservationServiceImpl reservationServiceImpl;

    @Test
    public void reserveTest () throws ReservationException {

        Reservation reservation = reservationMock();

        Search search = reservationServiceImpl.reserve(reservation);

        assertNotNull(search);
        verify(kafkaProducer).sendMessage(reservation);
    }

    @Test
    public void invalidReserveDateTest () throws ReservationException {

        Reservation reservation = invalidDateReservationMock();

        Assertions.assertThrows(ReservationException.class,()-> reservationServiceImpl.reserve(reservation));
    }

    @Test
    public void invalidReserveAgeTest () throws ReservationException {

        Reservation reservation = invalidAgeReservationMock();

        Assertions.assertThrows(ReservationException.class,()-> reservationServiceImpl.reserve(reservation));
    }

    @Test
    public void findReserveTest() throws ReservationException {

        Reservation reservation = reservationMock();
        when(reservationRepository.findById(Mockito.anyString())).thenReturn(Optional.of(reservation));

        assertNotNull(reservationServiceImpl.getOne(reservation.getId()));
    }

    @Test
    public void countReservesTest (){

        Reservation reservation = reservationMock();

        when(reservationRepository.countById(Mockito.anyString())).thenReturn(1L);

        Assertions.assertEquals(1L, reservationServiceImpl.getCountOfSearches(reservation.getId()));

    }
    public Reservation reservationMock (){
       return Reservation.createReservation("545","30/03/2024","10/10/2024", Arrays.asList(18,99));
    }

    public Reservation invalidDateReservationMock (){
        return Reservation.createReservation("545","30/03/2023","10/10/2024", Arrays.asList(5,7));
    }

    public Reservation invalidAgeReservationMock (){
        return Reservation.createReservation("545","30/03/2024","10/10/2024", Arrays.asList(0,7));
    }
}
