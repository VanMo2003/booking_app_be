package com.example.booking_app.repository;

import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.Statistical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticalRepository extends JpaRepository<Statistical, Long> {
    @Query(nativeQuery = true, value = """
        SELECT *\s
        FROM booking_app.statistical
        WHERE booking_app.statistical.hotel_id = :hotel
        and MONTH(booking_app.statistical.revenue_month) = MONTH(:revenueMonth) AND YEAR(booking_app.statistical.revenue_month) = YEAR(:revenueMonth);
    """)
    Optional<Statistical> findByHotelAndRevenueMonth(
            @Param("hotel") Long hotelId,
            @Param("revenueMonth") LocalDate revenueMonth);

    List<Statistical> findByHotel(Hotel hotel);
}
