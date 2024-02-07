package com.hotel.springboot.repository;

import com.hotel.springboot.payload.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation,String> {
    long countById (String id);
}
