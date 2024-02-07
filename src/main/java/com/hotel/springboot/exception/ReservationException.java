package com.hotel.springboot.exception;

public class ReservationException extends RuntimeException{

    private final String code;


    public  ReservationException (ReservationExceptionCause reservationExceptionCause){
        super(reservationExceptionCause.getCode().concat(": ").concat(reservationExceptionCause.getMessage()));
        this.code = reservationExceptionCause.getCode();
    }

    public String getCode() {
        return code;
    }

}
