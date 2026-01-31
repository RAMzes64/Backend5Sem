package com.example.courseWork5REST.Repositories;

import com.example.courseWork5REST.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
    boolean existsByName(String name);

    @Query("""
            SELECT r
            FROM Role r
            JOIN Staff s
            JOIN Hotel h
            WHERE h.id = ?1
            """)
    List<Role> findByHotel(Integer hotelId);
}
