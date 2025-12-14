package com.example.courseWork5REST.Repositories;

import com.example.courseWork5REST.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {
    @Query("SELECT r FROM Room r WHERE r.hotel.id = ?1 AND r.type.price BETWEEN ?2 AND ?3")
    List<Room> findRoomsByHotelAndPriceRange(Integer hotelId, Double minPrice, Double maxPrice);
}
