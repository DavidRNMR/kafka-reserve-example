package com.hotel.springboot.kafka;

import com.hotel.springboot.payload.Reservation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerTests {

    @Mock
    private KafkaTemplate<String, Reservation> kafkaTemplate;
    @InjectMocks
    private KafkaProducer kafkaProducer;
    @Captor
    private ArgumentCaptor<String> topicCaptor;
    @Captor
    private ArgumentCaptor<Reservation> reservationCaptor;

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Test
    public void sendMessageTest() {

        Reservation reservation = reservationMock();

        CompletableFuture<SendResult<String, Reservation>> completableFuture = new CompletableFuture<>();

        when(kafkaTemplate.send(eq(topicName), any())).thenReturn(completableFuture);

        kafkaProducer.sendMessage(reservation);

        verify(kafkaTemplate).send(topicCaptor.capture(), reservationCaptor.capture());
        assertEquals(topicName, topicCaptor.getValue());
        assertEquals(reservation, reservationCaptor.getValue());

        completableFuture.complete(new SendResult<>(null, null));
    }
    public Reservation reservationMock (){
        return Reservation.createReservation("545","30/03/2024","10/10/2024", Arrays.asList(18,25));
    }
}
