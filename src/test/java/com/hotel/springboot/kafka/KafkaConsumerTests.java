package com.hotel.springboot.kafka;

import com.hotel.springboot.payload.Reservation;
import com.hotel.springboot.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KafkaConsumerTests {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private KafkaConsumer kafkaConsumer;

    @Test
    public void consumeTest (){

        Reservation reservation = reservationMock();
        kafkaConsumer.consume(reservation);

        verify(reservationRepository).save(reservation);
    }

    public Reservation reservationMock (){
        return Reservation.createReservation("545","30/03/2024","10/10/2024", Arrays.asList(18,25));
    }
}
