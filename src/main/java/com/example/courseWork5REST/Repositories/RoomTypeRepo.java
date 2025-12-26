package com.example.courseWork5REST.Repositories;

import com.example.courseWork5REST.Models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RoomTypeRepo extends JpaRepository<RoomType, Integer> {
    boolean existsByName(String name);

    @Query("""
       SELECT DISTINCT r.type 
       FROM Room r 
       WHERE r.hotel.id = ?1 
       AND NOT EXISTS (
           SELECT 1 
           FROM Booking o 
           WHERE o.room = r 
           AND o.checkInDate < ?3 
           AND o.checkOutDate > ?2
           AND o.status NOT IN ('CANCELLED', 'COMPLETED')
       )
       """)
    List<RoomType> getAvailableByHotel(
            Integer hotelId,
            Date checkInDate,
            Date checkOutDate
    );
}
