package com.example.booking_app.repository;

import com.example.booking_app.entity.BookedRoom;
import com.example.booking_app.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long>{
    List<BookedRoom> findByArrivalDateOrDepartureDateAndHotel(LocalDate arrivalDate, LocalDate departureDate, Hotel hotel);
    @Query(nativeQuery = true, value = """
            SELECT *\s
            FROM booking_app.booked_room
            where (booking_app.booked_room.arrival_date between :arrivalDate and :departureDate
            or booking_app.booked_room.departure_date between :arrivalDate and  :departureDate
            or :arrivalDate between booking_app.booked_room.arrival_date and booking_app.booked_room.departure_date)
            and booking_app.booked_room.hotel_id = :hotel;
        """)
    List<BookedRoom> getAllBookedRoomIntersectArrivalDateAndDepartureDate(
            @Param("arrivalDate") LocalDate arrivalDate,
            @Param("departureDate") LocalDate departureDate,
            @Param("hotel") Long hotelId);
}
