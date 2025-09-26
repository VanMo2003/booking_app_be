package com.example.booking_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByHotel(Hotel hotel);
}
