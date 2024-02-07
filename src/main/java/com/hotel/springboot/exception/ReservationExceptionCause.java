package com.hotel.springboot.exception;



public enum ReservationExceptionCause {

    ERROR01("ERROR01","Reservation doesn't exist"),
    ERROR02("ERROR02","Invalid age"),
    ERROR03("ERROR04","Invalid date");

    private final String message;
    private final String code;

    ReservationExceptionCause(String code,String message) {
        this.code = code;
        this.message= message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}

