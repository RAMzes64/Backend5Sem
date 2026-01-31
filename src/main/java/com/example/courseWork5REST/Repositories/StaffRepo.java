package com.example.courseWork5REST.Repositories;

import com.example.courseWork5REST.DTO.StaffResponse;
import com.example.courseWork5REST.Models.Staff;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Integer> {

    @EntityGraph(attributePaths = {"role"})
    Optional<Staff> findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByEmail(String login);

    @Query("""
            SELECT new com.example.courseWork5REST.DTO.StaffResponse(
                s.id,
                s.login,
                s.role.name,
                s.email,
                s.firstName,
                s.lastName,
                s.birthDate,
                s.hotel.id,
                s.hotel.name
            )
            FROM Staff s
            JOIN s.hotel
            JOIN s.role
            WHERE s.hotel.id = ?1
            """)
    List<StaffResponse> findByHotel(Integer hotelId);
}
