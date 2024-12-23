package com.example.booking_app.repository;

import com.example.booking_app.entity.BookedRoom;
import com.example.booking_app.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long>{
//    boolean existsAllByCheckInAndCheckOutAndHotel(LocalDate checkIn, LocalDate checkOut, Hotel hotel);
    List<BookedRoom> findByArrivalDateOrDepartureDateAndHotel(LocalDate arrivalDate, LocalDate departureDate, Hotel hotel);
}
