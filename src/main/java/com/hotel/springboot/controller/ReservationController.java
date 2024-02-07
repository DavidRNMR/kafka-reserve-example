package com.hotel.springboot.controller;

import com.hotel.springboot.mapper.ReservationMapper;
import com.hotel.springboot.payload.Reservation;
import com.hotel.springboot.payload.ReservationResponse;
import com.hotel.springboot.payload.Search;
import com.hotel.springboot.payload.SearchCount;
import com.hotel.springboot.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/hotel")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper mapper;

    public ReservationController(ReservationService reservationService, ReservationMapper mapper) {
        this.reservationService = reservationService;
        this.mapper = mapper;
    }

    @PostMapping("/search")
    public ResponseEntity<Search> reserve (@Valid @RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.reserve(reservation));
    }

    @GetMapping("/count")
    public ResponseEntity<SearchCount> numberOfSearches (@RequestParam String searchId) {

        long number = reservationService.getCountOfSearches(searchId);

        Reservation reservation = reservationService.getOne(searchId);
        ReservationResponse reservationResponse = mapper.fromReservation(reservation);

        return ResponseEntity.ok(new SearchCount(searchId,reservationResponse,number));
    }
}
