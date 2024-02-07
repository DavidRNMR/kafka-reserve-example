package com.hotel.springboot.kafka;

import com.hotel.springboot.repository.ReservationRepository;
import com.hotel.springboot.payload.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private final ReservationRepository reservationRepository;

    public KafkaConsumer(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "myGroup")
    public void consume(Reservation reservation){

        reservationRepository.save(reservation);
        LOGGER.info(String.format("Message received with id -> %s", reservation.getId()));
    }
}
