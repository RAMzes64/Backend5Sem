package com.example.courseWork5REST.Repositories;

import com.example.courseWork5REST.DTO.HotelResponse;
import com.example.courseWork5REST.Models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {
    boolean existsByName(String name);
    boolean existsByAddressAndHouseNumber(String address, Integer houseNumber);

    @Query("SELECT DISTINCT h.city FROM Hotel h ORDER BY h.city")
    List<String> findDistinctCities();

    @Query("""
            SELECT  new com.example.courseWork5REST.DTO.HotelResponse(
            h.id,
            h.name,
            h.address,
            h.city,
            h.description,
            h.houseNumber,
            h.rating,
            h.img
            )
            FROM Hotel h
            WHERE h.city = ?1
            """)
    List<HotelResponse> findAllByCity(String city);
}
