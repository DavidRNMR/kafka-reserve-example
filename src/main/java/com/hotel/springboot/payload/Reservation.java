package com.hotel.springboot.payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class Reservation implements Serializable {
    @Id
    private String id;
    @NotBlank(message = "hotelId is mandatory")
    private String hotelId;
    @NotBlank(message = "check in is mandatory")
    private String checkIn;
    @NotBlank(message = "checkout is mandatory")
    private String checkOut;
    @NotEmpty(message = "at least one guest")
    private List<Integer> ages;

    private Reservation (String id, String hotelId, String checkIn, String checkOut, List<Integer> ages){
        this.id = id;
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
    }
    private Reservation (){
    }
    public static Reservation createReservation (String hotelId, String checkIn, String checkOut, List<Integer> ages){
        String id = UUID.randomUUID().toString();
        return new Reservation(id,hotelId,checkIn,checkOut,ages);
    }
    public String getId() {
        return id;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public List<Integer> getAges() {
        return ages;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", hotelId='" + hotelId + '\'' +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", ages=" + ages +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(hotelId, that.hotelId) && Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut) && Objects.equals(ages, that.ages);
    }
}
