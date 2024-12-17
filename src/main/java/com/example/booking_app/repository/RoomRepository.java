package com.example.booking_app.repository;

import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.Room;
import com.example.booking_app.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByHotel(Hotel hotel);

}
