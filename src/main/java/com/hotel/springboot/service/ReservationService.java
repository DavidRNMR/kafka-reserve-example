package com.hotel.springboot.service;

import com.hotel.springboot.exception.ReservationException;
import com.hotel.springboot.payload.Reservation;
import com.hotel.springboot.payload.Search;

public interface ReservationService {

    long getCountOfSearches(String id) throws ReservationException;
    Reservation getOne(String id) throws ReservationException;
    Search reserve(Reservation reservation) throws ReservationException;
}
