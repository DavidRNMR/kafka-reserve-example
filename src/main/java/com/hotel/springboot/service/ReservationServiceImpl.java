package com.hotel.springboot.service;

import com.hotel.springboot.kafka.KafkaProducer;
import com.hotel.springboot.payload.Reservation;
import com.hotel.springboot.payload.Search;
import com.hotel.springboot.repository.ReservationRepository;
import com.hotel.springboot.validation.ReservationValidation;
import com.hotel.springboot.exception.ReservationException;
import com.hotel.springboot.exception.ReservationExceptionCause;
import org.springframework.stereotype.Service;


@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final KafkaProducer kafkaProducer;

    public ReservationServiceImpl(ReservationRepository reservationRepository, KafkaProducer kafkaProducer) {
        this.reservationRepository = reservationRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public long getCountOfSearches(String id) throws ReservationException {
        return reservationRepository.countById(id);
    }

    public Reservation getOne(String id) throws ReservationException {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationException(ReservationExceptionCause.ERROR01));
    }

    public Search reserve(Reservation reservation) throws ReservationException {

        if (!ReservationValidation.isValidReservationDate(reservation)) {
            throw new ReservationException(ReservationExceptionCause.ERROR03);
        }
        if (!ReservationValidation.isValidReservationAge(reservation)) {
            throw new ReservationException(ReservationExceptionCause.ERROR02);
        }

        reservation = Reservation.createReservation(reservation.getHotelId(), reservation.getCheckIn(), reservation.getCheckOut(), reservation.getAges());
        Search search = new Search(reservation.getId());
        kafkaProducer.sendMessage(reservation);

        return search;
    }
}

