package com.example.courseWork5REST.Repositories;

import com.example.courseWork5REST.DTO.OrderResponse;
import com.example.courseWork5REST.Models.Booking;
import com.example.courseWork5REST.Models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

    @Query("""
        SELECT o FROM Booking o
        WHERE o.room.id = ?1
        AND o.status IN ('PENDING', 'CONFIRMED')
        AND ((o.checkInDate <= ?3 AND o.checkOutDate >= ?2))
        """)
    List<Booking> findOverlappingOrders(
            Integer roomId,
            Date checkInDate,
            Date checkOutDate
    );

    @Query("""
        SELECT new com.example.courseWork5REST.DTO.OrderResponse(
            o.id,
            o.orderDate,
            o.checkInDate,
            o.checkOutDate,
            o.details,
            o.client.id,
            o.room.id,
            rt.id,
            o.status
        )
        FROM Booking o
        JOIN o.client c
        JOIN o.room r
        JOIN r.type rt
        WHERE o.id = ?1
        """)
    Optional<OrderResponse> findByIdOrderResponse(Integer id);

    @Query("""
        SELECT new com.example.courseWork5REST.DTO.OrderResponse(
            o.id,
            o.orderDate,
            o.checkInDate,
            o.checkOutDate,
            o.details,
            o.client.id,
            o.room.id,
            rt.id,
            o.status
        )
        FROM Booking o
        JOIN o.client c
        JOIN o.room r
        JOIN r.type rt
        WHERE o.status = ?1
        """)
    List<OrderResponse> findByStatusOrderResponse(OrderStatus status);

    @Query("""
        SELECT new com.example.courseWork5REST.DTO.OrderResponse(
            o.id,
            o.orderDate,
            o.checkInDate,
            o.checkOutDate,
            o.details,
            o.client.id,
            o.room.id,
            rt.id,
            o.status
        )
        FROM Booking o
        JOIN o.client c
        JOIN o.room r
        JOIN r.type rt
        """)
    List<OrderResponse> findAllOrderResponse();

    @Query("""
        SELECT new com.example.courseWork5REST.DTO.OrderResponse(
            o.id,
            o.orderDate,
            o.checkInDate,
            o.checkOutDate,
            o.details,
            o.client.id,
            o.room.id,
            rt.id,
            o.status
        )
        FROM Booking o
        JOIN o.client c
        JOIN o.room r
        JOIN r.type rt
        WHERE o.client.id = ?1
        """)
    List<OrderResponse> findByClientIdOrderResponse(Integer clientId);
}