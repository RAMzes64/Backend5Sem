package com.example.courseWork5REST.Repositories;

import com.example.courseWork5REST.DTO.RoomResponse;
import com.example.courseWork5REST.Models.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {
    @Query("""
        SELECT new com.example.courseWork5REST.DTO.RoomResponse(
            r.id,
            r.type.id,
            r.type.name,
            r.hotel.id,
            r.hotel.name,
            r.status
        )
        FROM Room r
        JOIN r.type
        JOIN r.hotel
        WHERE r.hotel.id = ?1
    """)
    List<RoomResponse> findRoomResponseByHotelId(Integer hotelId);

    @Query("""
        SELECT new com.example.courseWork5REST.DTO.RoomResponse(
            r.id,
            r.type.id,
            r.type.name,
            r.hotel.id,
            r.hotel.name,
            r.status
        )
        FROM Room r
        JOIN r.type
        JOIN r.hotel
        WHERE r.id = ?1
    """)
    RoomResponse findRoomResponseById(Integer id);

    @Query("""
    SELECT r
    FROM Room r
    WHERE r.type.id = ?1
      AND r.hotel.id = ?2
      AND r.status = 'AVAILABLE'
      AND NOT EXISTS (
          SELECT 1
          FROM Booking b
          WHERE b.room = r
            AND b.checkInDate < ?4  
            AND b.checkOutDate > ?3 
            AND b.status IN ('CONFIRMED', 'PENDING', 'CHECKED_IN')
      )
""")
    Page<Room> findRoomByTypeAndHotel(Integer typeId,
                                      Integer hotelId,
                                      Date checkInDate,
                                      Date checkOutDate,
                                      Pageable pageable);

}
