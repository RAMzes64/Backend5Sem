package com.example.courseWork5REST.Repositories;

import com.example.courseWork5REST.Models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {
    boolean existsByName(String name);
    boolean existsByAddress(String address);
    void deleteByName(String name);
}
