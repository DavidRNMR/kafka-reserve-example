package com.hotel.springboot.payload;

public class SearchCount {

    private String searchId;
    private ReservationResponse reservation;
    private long count;

    public String getSearchId() {
        return searchId;
    }

    public SearchCount(String searchId, ReservationResponse reservation, long count) {
        this.searchId = searchId;
        this.reservation = reservation;
        this.count = count;
    }

    public void setReservation(ReservationResponse reservation) {
        this.reservation = reservation;
    }

    public ReservationResponse getReservation() {
        return reservation;
    }
    public long getCount() {
        return count;
    }

}
